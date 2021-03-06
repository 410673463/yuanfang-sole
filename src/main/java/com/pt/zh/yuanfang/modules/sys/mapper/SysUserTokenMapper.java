package com.pt.zh.yuanfang.modules.sys.mapper;

import com.pt.zh.yuanfang.common.mapper.MyMapper;
import com.pt.zh.yuanfang.modules.sys.entity.SysUserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserTokenMapper extends MyMapper<SysUserToken> {
}