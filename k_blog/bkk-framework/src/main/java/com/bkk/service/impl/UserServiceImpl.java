package com.bkk.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.constants.RedisConstants;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.dto.EmailRegisterDTO;
import com.bkk.domain.dto.UserDTO;
import com.bkk.domain.entity.Menu;
import com.bkk.domain.entity.User;
import com.bkk.domain.entity.WebConfig;
import com.bkk.domain.vo.OnlineUserVo;
import com.bkk.domain.vo.SystemUserVO;
import com.bkk.domain.vo.UserVO;
import com.bkk.enums.LoginTypeEnum;
import com.bkk.exception.SystemException;
import com.bkk.handler.security.SecurityListener;
import com.bkk.mapper.UserMapper;
import com.bkk.service.*;
import com.bkk.utils.BaseContext;
import com.bkk.utils.BeanCopyUtils;
import com.bkk.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import java.nio.channels.ReadPendingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.bkk.constants.SqlConf.LIMIT_ONE;
import static com.bkk.enums.AppHttpCodeEnum.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final RedisCache redisCache;

    private final PasswordEncoder passwordEncoder;

    private final WebConfigService webConfigService;

    private final EmailService emailService;

    private final MenuService menuService;

    private final SecurityListener securityListener;



    /**
     * 更新用户信息
     * @param userDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updateUser(UserDTO userDTO) {
        Long userId = BaseContext.getCurrentId();
        User user = getById(userId);
        user.setNickname(userDTO.getNickname());
        user.setIntro(userDTO.getIntro());
        user.setWebSite(userDTO.getWebSite());
        user.setAvatar(userDTO.getAvatar());
        user.setEmail(userDTO.getEmail());
        boolean update = updateById(user);
        return update ? ResponseResult.okResult("修改信息成功") : ResponseResult.errorResult(FAILURE.getCode(), ERROR_DEFAULT.getMsg());
    }

    /**
     * 邮箱注册
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult emailRegister(EmailRegisterDTO dto) {
        //参数判断
        checkEmail(dto.getEmail());

        checkCode(RedisConstants.EMAIL_CODE + dto.getEmail(), dto.getCode());

        if (StringUtils.isNotBlank(dto.getNickname())) {
            throw new SystemException(PARAMS_ILLEGAL);
        }
        if (StringUtils.isNotBlank(dto.getPassword())) {
            throw new SystemException(PASSWORD_ILLEGAL);
        }
        emailExist(dto.getEmail());
        checkNickname(dto.getNickname());

        WebConfig config = webConfigService.getOne(new QueryWrapper<WebConfig>().last(LIMIT_ONE));

        String password = passwordEncoder.encode(dto.getPassword());

        User user = User.builder().username(dto.getEmail()).nickname(dto.getNickname())
                .email(dto.getEmail()).avatar(config.getTouristAvatar())
                .loginType(LoginTypeEnum.EMAIL.getType())
                .password(password)
                .build();

        boolean save = save(user);
        redisCache.deleteObject(RedisConstants.EMAIL_CODE + dto.getEmail());

        return save ? ResponseResult.okResult("注册成功") : ResponseResult.errorResult(ERROR_DEFAULT.getMsg());
    }

    /**
     * 用户绑定邮箱，发送验证码
     * @param email
     * @return
     */
    @Override
    public ResponseResult sendEmailCode(String email) {
        checkEmail(email);
        try {
            emailService.sendCode(email);
            return ResponseResult.okResult("验证码已发送，请前往邮箱查看!!");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseResult.errorResult(ERROR_DEFAULT.getMsg());
        }

    }

    /**
     * 换绑邮箱
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult bindEmail(UserDTO dto) {
        emailExist(dto.getEmail());
        checkNickname(dto.getEmail());
        String key =  RedisConstants.EMAIL_CODE + dto.getEmail();
        checkCode(key, dto.getCode());

        User user = getById(BaseContext.getCurrentId());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getEmail());
        boolean update = updateById(user);
        redisCache.deleteObject(key);
        return update ? ResponseResult.okResult("绑定邮箱成功") : ResponseResult.errorResult(ERROR_DEFAULT.getMsg());
    }

    /**
     *获取当前登录用户详情
     * @return
     */
    @Override
    public ResponseResult getCurrentUserInfo() {
        return ResponseResult.okResult(baseMapper.getById(BaseContext.getCurrentId()));
    }

    /**
     * 获取当前登录用户所拥有的菜单权限
     * @return
     */
    @Override
    public ResponseResult getUserMenu() {
        List<Integer> menuIds = baseMapper.getMenuId(BaseContext.getCurrentId());
        List<Menu> menus = menuService.listByIds(menuIds);
        List<Menu> menuTree = menuService.listMenuTree(menus);
        return ResponseResult.okResult(menuTree);
    }

    /**
     * 用户列表
     * @param pageNo
     * @param pageSize
     * @param username
     * @param loginType
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize, String username, Integer loginType) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Objects.nonNull(username) && !username.equals(""), User::getNickname, username);
        queryWrapper.eq(Objects.nonNull(loginType), User::getLoginType, loginType);
        Page<User> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);
        List<User> records = page.getRecords();
        List<UserVO> userVOS = records.stream()
                .map(user -> BeanCopyUtils.copyBean(user, UserVO.class))
                .collect(Collectors.toList());

        Page<UserVO> userVoPage = new Page<>();
        BeanUtils.copyProperties(page, userVoPage, "records");
        userVoPage.setRecords(userVOS);
        return ResponseResult.okResult(userVoPage);
    }

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult info(Long id) {
        User user = baseMapper.selectById(id);
        SystemUserVO systemUserVO = BeanCopyUtils.copyBean(user, SystemUserVO.class);
        return ResponseResult.okResult(systemUserVO);
    }

    /**
     * 后台更新用户信息
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(SystemUserVO user) {
        User bean = BeanCopyUtils.copyBean(user, User.class);
        int update = baseMapper.updateById(bean);
        return update > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("修改失败！");
    }

    /**
     * 删除/批量删除 用户
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
     * 在线用户列表
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public ResponseResult online(Integer pageNo, Integer pageSize, String name) {
        List<OnlineUserVo> onlineUsers = SecurityListener.ONLINE_USERS;

        //根据关键词过滤
        if (StringUtils.isNotBlank(name)) {
            onlineUsers = onlineUsers.stream().filter(item -> item.getNickname().contains(name)).collect(Collectors.toList());
        }
        //排序
        onlineUsers.sort((o1, o2) -> DateUtil.compare(o2.getLoginTime(), o1.getLoginTime()));
        int fromIndex = (pageNo-1) * pageSize;
        int toIndex = onlineUsers.size() - fromIndex > pageSize ? fromIndex + pageSize : onlineUsers.size();
        List<OnlineUserVo> userOnlineList = onlineUsers.subList(fromIndex, toIndex);

        Map<String,Object> map = new HashMap<>();
        map.put("total",onlineUsers.size());
        map.put("records",userOnlineList);
        return ResponseResult.okResult(map);
    }

    /**
     * 踢出用户
     * @param token
     * @return
     */
    @Override
    public ResponseResult kick(String token) {
        //获取userid
        Long userId = BaseContext.getCurrentId();
        //删除redis中的用户信息
        redisCache.deleteObject("adminlogin:" + userId);

        securityListener.kickOutEvent(token);
        return ResponseResult.okResult();
    }

    /**
     * 修改密码
     * @param map
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updatePassword(Map<String, String> map) {

        User user = baseMapper.selectById(BaseContext.getCurrentId());
        if (Objects.isNull(user)) {
            throw new SystemException(ERROR_USER_NOT_EXIST);
        }

        String oldPassword = map.get("oldPassword");
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new SystemException("旧密码输入不正确!");
        }
        String newPassword = map.get("newPassword");
        String confirmPassword = map.get("confirmPassword");
        if (!newPassword.equalsIgnoreCase(confirmPassword)) {
            throw new SystemException("两次密码输入不一致!");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        int update = baseMapper.updateById(user);
        return update > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("修改失败!");
    }

    /**
     * 忘记密码，重新设置
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult blogUpdatePassword(EmailRegisterDTO dto) {
        checkEmail(dto.getEmail());
        String key =  RedisConstants.EMAIL_CODE + dto.getEmail();
        checkCode(key, dto.getCode());
        User user = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, dto.getEmail()));
        if (Objects.isNull(user)) {
            throw new SystemException(ERROR_MUST_REGISTER);
        }

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        int update = baseMapper.updateById(user);
        redisCache.deleteObject(key);

        return update > 0 ? ResponseResult.okResult() : ResponseResult.errorResult(ERROR_DEFAULT);
    }

    //---------------自定义方法开始-------------
    public void checkEmail(String email){
        boolean matches = Pattern.compile("\\w+@{1}\\w+\\.{1}\\w+").matcher(email).matches();
        if (!matches) {
            throw new SystemException(EMAIL_ERROR);
        }
    }

    private void emailExist(String email) {
        Integer count = count(new LambdaQueryWrapper<User>().eq(User::getEmail, email));
        if (count > 0) {
            throw new SystemException(EMAIL_IS_EXIST);
        }
    }

    private void checkCode(String key, String sourCode) {
        Object code = redisCache.getCacheObject(key);
        if (code == null || !code.equals(sourCode)) {
            throw new SystemException(ERROR_EXCEPTION_MOBILE_CODE);
        }
    }

    private void checkNickname(String nickname) {
        Integer count = count(new LambdaQueryWrapper<User>().eq(User::getNickname, nickname));
        if (count > 0) {
            throw new SystemException(NICKNAME_EXIST);
        }
    }
}
