package com.pt.zh.yuanfang.modules.sys.entity;

import com.pt.zh.yuanfang.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Table(name = "`sys_user`")
@Data
public class SysUser extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 盐
     */
    @Column(name = "`salt`")
    private String salt;

    /**
     * 邮箱
     */
    @Column(name = "`email`")
    private String email;

    /**
     * 手机号
     */
    @Column(name = "`mobile`")
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    @Column(name = "`status`")
    private Byte status;

    /**
     * 机构ID
     */
    @Column(name = "`dept_id`")
    private Integer deptId;


    @Transient
    private String roleNames;
    @Transient
    private String deptName;
    @Transient
    private SysDept dept;
    @Transient
    private List<SysUserRole> userRoles = new ArrayList<>();
}