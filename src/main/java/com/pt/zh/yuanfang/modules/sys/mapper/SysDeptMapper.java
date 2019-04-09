package com.pt.zh.yuanfang.modules.sys.mapper;

import com.pt.zh.yuanfang.common.mapper.MyMapper;
import com.pt.zh.yuanfang.modules.sys.entity.SysDept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface SysDeptMapper extends MyMapper<SysDept> {




    @Select(
            "select * from sys_dept"
    )
    List<SysDept> findAll();
}