package com.pt.zh.yuanfang.modules.sys.entity;

import com.pt.zh.yuanfang.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "`sys_user_role`")
public class SysUserRole extends BaseEntity implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "`user_id`")
    private Integer userId;
    @Column(name = "`role_id`")
    private Integer roleId;
}