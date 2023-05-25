package com.bkk.mapper;
import com.bkk.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 系统管理-权限资源表 (Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-07 18:05:33
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
