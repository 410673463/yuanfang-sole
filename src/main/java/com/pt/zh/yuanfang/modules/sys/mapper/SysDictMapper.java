package com.pt.zh.yuanfang.modules.sys.mapper;

import com.pt.zh.yuanfang.common.mapper.MyMapper;
import com.pt.zh.yuanfang.modules.sys.entity.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysDictMapper extends MyMapper<SysDict> {

    @Select(
            " select *   from sys_dict  where label = #{label,jdbcType=VARCHAR}"
    )
    List<SysDict> findByLable(@Param(value="label") String label);

    @Select(
            "select * from sys_dict"
    )
    List<SysDict> findPage();
    @Select(
            "select * from sys_dict where label like '%${label}%'"
    )
    List<SysDict> findPageByLabel(@Param(value="label") String label);
}