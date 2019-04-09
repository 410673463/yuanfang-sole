package com.pt.zh.yuanfang.modules.sys.mapper;

import com.pt.zh.yuanfang.common.mapper.MyMapper;
import com.pt.zh.yuanfang.modules.sys.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysLogMapper extends MyMapper<SysLog> {
    @Select(" select * from sys_log")
    List<SysLog> findPage();

    @Select(" select * from sys_log where user_name like '${userName}%'")
    List<SysLog> findPageByUserName(@Param(value = "userName") String userName);
}