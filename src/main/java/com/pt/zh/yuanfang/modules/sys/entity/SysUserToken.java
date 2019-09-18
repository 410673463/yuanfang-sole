package com.pt.zh.yuanfang.modules.sys.entity;

import com.pt.zh.yuanfang.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`sys_user_token`")
public class SysUserToken  extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`user_id`")
    private Integer userId;

    /**
     * token
     */
    @Column(name = "`token`")
    private String token;

    /**
     * 过期时间
     */
    @Column(name = "`expire_time`")
    private Date expireTime;

}