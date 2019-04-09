package com.pt.zh.yuanfang.modules.sys.mapper;

import com.pt.zh.yuanfang.common.mapper.MyMapper;
import com.pt.zh.yuanfang.modules.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserMapper extends MyMapper<SysUser> {
    @Select(
            " select u.id, u.name, u.salt, u.email, u.mobile, u.status, u.dept_id, u.create_by, u.create_time, u.last_update_by, u.last_update_time, (select d.name from sys_dept d where d.id = u.dept_id) deptName from sys_user u   where u.name = #{name,jdbcType=VARCHAR}"
    )
    SysUser findByName(@Param(value = "name") String name);

    @Select(
            "select * from sys_user where name= #{account} and password = #{password}"
    )
    SysUser singIn(@Param(value = "account") String account, @Param(value = "password") String password);

    @Select(
            "select u.id, u.name, u.salt, u.email, u.mobile, u.status, u.dept_id, u.create_by, u.create_time, u.last_update_by, u.last_update_time, (select d.name from sys_dept d where d.id = u.dept_id) deptName from sys_user u"
    )
    List<SysUser> findPage();

    @Select(
            "select u.id, u.name, u.salt, u.email, u.mobile, u.status, u.dept_id, u.create_by, u.create_time, u.last_update_by, u.last_update_time, (select d.name from sys_dept d where d.id = u.dept_id) deptName from sys_user u where u.name like '%${name}%'"
    )
    List<SysUser> findPageByName(@Param(value = "name") String name);

    @Select(" select u.id, u.name, u.salt, u.email, u.mobile, u.status, u.dept_id, u.create_by, u.create_time, u.last_update_by, u.last_update_time, (select d.name from sys_dept d where d.id = u.dept_id) deptName from sys_user u where u.name like '%#{patternName}%'   and u.email like '%#{patternEmail}%'")
    List<SysUser> findPageByNameAndEmail(@Param(value = "name") String name, @Param(value = "email") String email);
}