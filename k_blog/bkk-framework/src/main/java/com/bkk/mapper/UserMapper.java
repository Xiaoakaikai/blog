package com.bkk.mapper;
import com.bkk.domain.entity.User;
import com.bkk.domain.vo.SystemUserVO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 系统管理-用户基础信息表(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-27 21:01:54
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    SystemUserVO getById(Long id);

    List<Integer> getMenuId(Long userId);

    String getRoleNameById(Long userId);

    void updateLoginInfo(@Param("loginId")Object loginId, @Param("ip") String ip, @Param("cityInfo")String cityInfo,
                         @Param("os") String os, @Param("browser") String browser);
}
