package com.pt.zh.yuanfang.modules.sys.mapper;

import com.pt.zh.yuanfang.common.mapper.MyMapper;
import com.pt.zh.yuanfang.modules.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysMenuMapper extends MyMapper<SysMenu> {


    @Select(
            "select * from sys_menu"
    )
    List<SysMenu> findAll();

    @Select(
            "select m.* from sys_menu m, sys_user u, sys_user_role ur, sys_role_menu rm" +
                    "  where u.name = #{userName} and u.id = ur.user_id " +
                    "  and ur.role_id = rm.role_id and rm.menu_id = m.id"
    )
    List<SysMenu> findByUserName(@Param(value = "userName") String userName);

    @Select(
            " select m.* from sys_menu m, sys_role_menu rm" +
                    "    where rm.role_id = #{roleId}" +
                    "    and m.id = rm.menu_id"
    )
    List<SysMenu> findRoleMenus(@Param(value = "roleId") Integer roleId);

    @Select("select * from sys_menu")
    List<SysMenu> findPage();

    @Select("select * from sys_menu where name like '%${name}%'")
    List<SysMenu> findPageByName(@Param(value = "name") String name);
}