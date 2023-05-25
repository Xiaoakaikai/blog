package com.bkk.mapper;
import com.bkk.domain.entity.Photo;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 照片(Photo)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-13 21:26:21
 */
@Mapper
public interface PhotoMapper extends BaseMapper<Photo> {

    void movePhoto(@Param("idList") List<Long> ids, @Param("albumId") Long albumId);

}
