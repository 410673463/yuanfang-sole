package com.pt.zh.yuanfang.modules.sys.mapper;

import com.pt.zh.yuanfang.common.mapper.MyMapper;
import com.pt.zh.yuanfang.modules.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface SysUserRoleMapper extends MyMapper<SysUserRole> {

    @Select(
            " select  *   from sys_user_role    where user_id = #{userId}"
    )
    List<SysUserRole> findUserRoles(@Param(value = "userId") Integer userId);

    @Delete(
            "delete from sys_user_role    where user_id = #{userId}"
    )
    int deleteByUserId(@Param(value = "userId") Integer userId);
}