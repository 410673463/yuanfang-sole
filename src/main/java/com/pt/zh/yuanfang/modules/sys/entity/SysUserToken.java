package com.pt.zh.yuanfang.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`sys_user_token`")
public class SysUserToken  implements Serializable {
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

    /**
     * 创建人
     */
    @Column(name = "`create_by`")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "`last_update_by`")
    private String lastUpdateBy;

    /**
     * 更新时间
     */
    @Column(name = "`last_update_time`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateTime;

}