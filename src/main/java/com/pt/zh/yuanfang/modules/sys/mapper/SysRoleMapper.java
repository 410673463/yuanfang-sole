package com.pt.zh.yuanfang.modules.sys.mapper;

import com.pt.zh.yuanfang.common.mapper.MyMapper;
import com.pt.zh.yuanfang.modules.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface SysRoleMapper extends MyMapper<SysRole> {

    @Select(
            "select * from sys_role"
    )
    List<SysRole> findAll();

    @Select(
            " select    *  from sys_role    where name = #{name,jdbcType=VARCHAR}"
    )
    List<SysRole> findByName(@Param(value = "name") String name);

    @Select(
            "select * from sys_role"
    )
    List<SysRole> findPage();

    @Select("select * from sys_role where name like '%${name}%'")
    List<SysRole> findPageByName(@Param(value = "name") String name);
}