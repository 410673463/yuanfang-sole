package com.pt.zh.yuanfang.modules.core.service;

import com.pt.zh.yuanfang.modules.sys.entity.SysUser;

public interface ITokenUtil {

    /**
     * 生成token
     * @param user
     * @return
     */
    String getToken(SysUser user);

    /**
     * 更新token
     * @param user
     */
    void updateToken(SysUser user,String oldToken,long breathTime);
}
