package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.constants.SqlConf;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.FriendLink;
import com.bkk.domain.vo.FriendLinkVO;
import com.bkk.exception.SystemException;
import com.bkk.mapper.FriendLinkMapper;
import com.bkk.service.EmailService;
import com.bkk.service.FriendLinkService;
import com.bkk.utils.BeanCopyUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.bkk.constants.SqlConf.LIMIT_ONE;
import static com.bkk.enums.AppHttpCodeEnum.DATA_TAG_IS_TOP;
import static com.bkk.enums.AppHttpCodeEnum.FRIEND_LINK_IS_TOP;
import static com.bkk.enums.FriendLinkEnum.DOWN;
import static com.bkk.enums.FriendLinkEnum.UP;

/**
 * 友情链接表(FriendLink)表服务实现类
 *
 * @author makejava
 * @since 2023-03-30 17:31:44
 */
@Service("friendLinkService")
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 获取友链链表
     * @return
     */
    @Override
    public ResponseResult friendLinkList() {
        //添加条件
        LambdaQueryWrapper<FriendLink> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(FriendLink::getSort);
        List<FriendLink> friendLinks = list(queryWrapper);
        List<FriendLinkVO> friendLinkVOS = BeanCopyUtils.copyBeanList(friendLinks, FriendLinkVO.class);
        return ResponseResult.okResult(friendLinkVOS);
    }

    /**
     * 后台获取友链列表
     * @param pageNo
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize, String name, Integer status) {
        LambdaQueryWrapper<FriendLink> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Objects.nonNull(name), FriendLink::getName, name);
        queryWrapper.eq(Objects.nonNull(status), FriendLink::getStatus, status);
        queryWrapper.orderByDesc(FriendLink::getSort);
        Page<FriendLink> page = new Page<>();
        page(page, queryWrapper);
        return ResponseResult.okResult(page);
    }

    /**
     * 新增友链
     * @param friendLink
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult create(FriendLink friendLink) {
        int insert = baseMapper.insert(friendLink);
        return insert > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("添加失败！");
    }

    /**
     * 修改友链
     * @param friendLink
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(FriendLink friendLink) {
        int i = baseMapper.updateById(friendLink);
        //审核通过发送邮件通知
        if(friendLink.getStatus().equals(UP.getCode())){
            emailService.friendPassSendEmail(friendLink.getEmail());
        }
        //审核未通过发送邮件通知
        if(friendLink.getStatus().equals(DOWN.getCode())){
            emailService.friendFailedSendEmail(friendLink.getEmail(), friendLink.getReason());
        }
        return i > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("修改失败！");
    }

    /**
     * 置顶友链
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult top(Long id) {
        FriendLink one = baseMapper.selectOne(new LambdaQueryWrapper<FriendLink>()
                .last(LIMIT_ONE).orderByDesc(FriendLink::getSort));
        FriendLink friendLink = baseMapper.selectById(id);
        if (one.getSort().equals(friendLink.getSort())) throw new SystemException(FRIEND_LINK_IS_TOP);
        friendLink.setSort(one.getSort() + 1);
        baseMapper.updateById(friendLink);
        return ResponseResult.okResult();
    }

    /**
     * 删除友链
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult remove(List<Long> ids) {
        int i = baseMapper.deleteBatchIds(ids);
        return i > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("删除失败！");
    }

    /**
     * 申请友链
     * @param friendLink
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(FriendLink friendLink) {
        if (StringUtils.isBlank(friendLink.getUrl())) {
            throw new SystemException("输入正确的网址!");
        }

        //如果已经申请过友链 再次接入则会进行下架处理 需重新审核
        FriendLink entity = baseMapper.selectOne(new LambdaQueryWrapper<FriendLink>()
                .eq(FriendLink::getUrl, friendLink.getUrl()));
        if (entity != null) {
            friendLink.setId(entity.getId());
            baseMapper.updateById(friendLink);
        }else {
            baseMapper.insert(friendLink);
        }

        //不影响用户体验 新一个线程操作邮箱发送
        threadPoolTaskExecutor.execute(() -> emailService.emailNoticeMe("友链接入通知", "网站有新的友链接入啦("+friendLink.getUrl()+")，快去审核吧!!!"));
        return ResponseResult.okResult();
    }
}

