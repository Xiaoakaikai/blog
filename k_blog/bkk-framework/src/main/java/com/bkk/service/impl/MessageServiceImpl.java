package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Message;
import com.bkk.mapper.MessageMapper;
import com.bkk.service.MessageService;
import com.bkk.utils.IpUtils;
import lombok.RequiredArgsConstructor;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (Message)表服务实现类
 *
 * @author makejava
 * @since 2023-04-13 20:25:47
 */
@Service("messageService")
@RequiredArgsConstructor
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    private final HttpServletRequest request;

    /**
     * 后台获取留言列表
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public ResponseResult list(Integer pageNo, Integer pageSize, String name) {
        LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), Message::getNickname, name).orderByDesc(Message::getCreateTime);
        Page<Message> page = new Page<>(pageNo, pageSize);
        page(page, queryWrapper);
        return ResponseResult.okResult(page);
    }

    /**
     * 留言审核通过
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult passBatch(List<Long> ids) {
        baseMapper.passBatch(ids);
        return ResponseResult.okResult();
    }

    /**
     * 删除留言
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
     * 留言列表
     * @return
     */
    @Override
    public ResponseResult webMessage() {
        // 查询留言列表
        List<Message> messageList = baseMapper.selectList(new LambdaQueryWrapper<Message>()
                .select(Message::getId, Message::getNickname, Message::getAvatar,
                        Message::getContent, Message::getTime));
        return ResponseResult.okResult(messageList);
    }

    /**
     * 添加留言
     * @param message
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(Message message) {
        //获取ip
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        message.setIpAddress(ipAddress);
        message.setIpSource(ipSource);
        int insert = baseMapper.insert(message);
        return insert > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("发送失败！");
    }
}

