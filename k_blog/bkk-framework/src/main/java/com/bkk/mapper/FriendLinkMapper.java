package com.bkk.mapper;
import com.bkk.domain.entity.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 友情链接表(FriendLink)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-30 17:31:43
 */
@Mapper
public interface FriendLinkMapper extends BaseMapper<FriendLink> {

}
