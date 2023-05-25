package com.bkk.mapper;
import com.bkk.domain.entity.PhotoAlbum;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 相册(PhotoAlbum)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-13 21:22:47
 */
@Mapper
public interface PhotoAlbumMapper extends BaseMapper<PhotoAlbum> {

}
