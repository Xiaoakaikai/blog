package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bkk.constants.RedisConstants;
import com.bkk.constants.SystemConstants;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Article;
import com.bkk.domain.vo.CategoryCountVO;
import com.bkk.domain.vo.ContributeVO;
import com.bkk.domain.vo.HomeDataVO;
import com.bkk.mapper.*;
import com.bkk.service.HomeService;
import com.bkk.service.MessageService;
import com.bkk.utils.DateUtils;
import com.bkk.utils.IpUtils;
import com.bkk.utils.RedisCache;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static com.bkk.constants.DashboardNotification.DASHBOARD_NOTIFICATION;
import static com.bkk.constants.SystemConstants.ARTICLE_STATUS_NORMAL;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final ArticleMapper articleMapper;

    private final CategoryMapper categoryMapper;

    private final TagsMapper tagsMapper;

    private final UserMapper userMapper;

    private final MessageMapper messageMapper;

    private final RedisCache redisCache;

    private final UserLogMapper userLogMapper;

    /**
     * 后台首页初始化
     * @return
     */
    @Override
    public ResponseResult init() {
        //文章排行
        List<Article> blogArticles = articleMapper.selectList(new LambdaQueryWrapper<Article>()
                .select(Article::getQuantity, Article::getTitle, Article::getId)
                .eq(Article::getIsPublish, ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getQuantity).last("limit 6"));
        //文章贡献度
        Map<String, Object> contribute = this.contribute();
        //分类统计
        Map<String, Object> categoryCount = this.categoryCount();
        //用户访问量
        List<Map<String, Object>> userAccess = this.userAccess();

        List<Map<String,Object>> tagsList = tagsMapper.countTags();
        //弹出框

        HomeDataVO dto = HomeDataVO.builder().dashboard(DASHBOARD_NOTIFICATION)
                .categoryList(categoryCount).contribute(contribute).blogArticles(blogArticles).userAccess(userAccess).tagsList(tagsList).build();
        return ResponseResult.okResult(dto);
    }

    /**
     * 文章、留言、用户、ip统计
     * @return
     */
    @Override
    public ResponseResult lineCount() {
        Map<String,Integer> map = new HashMap<>();
        map.put("article", articleMapper.selectCount(null));
        map.put("message",messageMapper.selectCount(null));
        map.put("user",userMapper.selectCount(null));
        map.put("viewsCount",(Integer) getViewsCount());
        return ResponseResult.okResult(map);
    }

    /**
     * 增加访问量
     * @param request
     * @return
     */
    @Override
    public ResponseResult report(HttpServletRequest request) {
        // 获取ip
        String ipAddress = IpUtils.getIpAddress(request);
        // 获取访问设备
        UserAgent userAgent = IpUtils.getUserAgent(request);
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        // 生成唯一用户标识
        String uuid = ipAddress + browser.getName() + operatingSystem.getName();
        String md5 = DigestUtils.md5DigestAsHex(uuid.getBytes());
        // 判断是否访问
        if (!redisCache.sIsMember(RedisConstants.UNIQUE_VISITOR, md5)) {
            // 统计游客地域分布
            String ipSource = IpUtils.getCityInfo(ipAddress);
            if (StringUtils.isNotBlank(ipSource)) {
                ipSource = ipSource.substring(0, 2)
                        .replaceAll(SystemConstants.PROVINCE, "")
                        .replaceAll(SystemConstants.CITY, "");
                redisCache.incrementCacheMapValue(RedisConstants.VISITOR_AREA, ipSource, 1L);
            } else {
                redisCache.incrementCacheMapValue(RedisConstants.VISITOR_AREA, SystemConstants.UNKNOWN, 1L);
            }
            // 访问量+1
            redisCache.incr(RedisConstants.BLOG_VIEWS_COUNT, 1);
            // 保存唯一标识
            redisCache.sAdd(RedisConstants.UNIQUE_VISITOR, md5);
        }
        return ResponseResult.okResult();
    }


    //--------------自定义方法开始---------------

    public static List<String> getMonths() {
        List<String> dateList = new ArrayList<String>();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        dateList.add(DateUtils.formateDate(calendar.getTime(), DateUtils.YYYY_MM_DD));
        while (date.after(calendar.getTime())) { //倒序时间,顺序after改before其他相应的改动。
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(DateUtils.formateDate(calendar.getTime(), DateUtils.YYYY_MM_DD));
        }
        return dateList;
    }

    /**
     * 获取文章贡献度
     * @return
     */
    public Map<String, Object> contribute() {
        Map<String, Object> map = new HashMap<>();
        List<Object> contributes = new ArrayList<>();
        List<Object> result = new ArrayList<>();
        List<String> months = getMonths();
        String lastTime = months.get(0), nowTime = months.get(months.size() - 1);
        List<ContributeVO> articles = articleMapper.contribute(lastTime, nowTime);
        months.forEach(item -> {
            List<Object> list = new ArrayList<>();
            list.add(item);
            List<ContributeVO> collect = articles.stream().filter(article -> article.getDate().equals(item)).collect(Collectors.toList());
            if (!collect.isEmpty()) list.add(collect.get(0).getCount());
            else list.add(0);
            result.add(list);
        });
        contributes.add(lastTime);
        contributes.add(nowTime);
        map.put("contributeDate", contributes);
        map.put("blogContributeCount", result);
        return map;
    }

    /**
     * 分类统计
     * @return
     */
    public Map<String, Object> categoryCount(){
        Map<String, Object> map = new HashMap<>();
        List<CategoryCountVO> result = categoryMapper.countArticle();
        List<String> list = new ArrayList<>();
        result.forEach(item -> list.add(item.getName()));
        map.put("result",result);
        map.put("categoryList",list);
        return map;
    }

    /**
     * 获取用户访问数据
     * @return
     */
    public List<Map<String,Object>> userAccess() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 7);
        List<Map<String,Object>> userAccess = userLogMapper.getUserAccess(DateUtils.formateDate(calendar.getTime(), DateUtils.YYYY_MM_DD));
        return userAccess;
    }

    /**
     * 获取网站访问量
     * @return
     */
    private Object getViewsCount() {
        Object count = redisCache.getCacheObject(RedisConstants.BLOG_VIEWS_COUNT);
        Object viewsCount = Optional.ofNullable(count).orElse(0);
        return viewsCount;
    }
}
