package com.bkk.mapper;
import com.bkk.domain.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (Message)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-13 20:25:46
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    void passBatch(@Param("ids") List<Long> ids);

}
