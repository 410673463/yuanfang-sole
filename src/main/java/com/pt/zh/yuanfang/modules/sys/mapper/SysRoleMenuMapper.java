package com.pt.zh.yuanfang.modules.sys.mapper;

import com.pt.zh.yuanfang.common.mapper.MyMapper;
import com.pt.zh.yuanfang.modules.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface SysRoleMenuMapper extends MyMapper<SysRoleMenu> {
    @Select(
            "select  *  from sys_role_menu  where role_id = #{roleId}"
    )
    List<SysRoleMenu> findRoleMenus(@Param(value = "roleId") Integer roleId);

    @Select(
            "select  *  from sys_role_menu "
    )
    List<SysRoleMenu> findAll();

    @Delete(
            "delete from sys_role_menu where role_id = #{roleId}"
    )
    int deleteByRoleId(@Param(value = "roleId") Integer roleId);
}