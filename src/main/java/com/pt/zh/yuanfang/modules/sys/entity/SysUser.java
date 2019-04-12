package com.pt.zh.yuanfang.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Table(name = "`sys_user`")
@Data
public class SysUser implements Serializable {
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

    /**
     * 是否删除  -1：已删除  0：正常
     */
    @Column(name = "`del_flag`")
    private Byte delFlag;
    @Transient
    private String roleNames;
    @Transient
    private String deptName;
    @Transient
    private SysDept dept;
    @Transient
    private List<SysUserRole> userRoles = new ArrayList<>();
}