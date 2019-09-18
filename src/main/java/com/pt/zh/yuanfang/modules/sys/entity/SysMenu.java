package com.pt.zh.yuanfang.modules.sys.entity;

import com.pt.zh.yuanfang.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Table(name = "`sys_menu`")
public class SysMenu extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜单名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 父菜单ID，一级菜单为0
     */
    @Column(name = "`parent_id`")
    private Integer parentId;

    /**
     * 菜单URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址)
     */
    @Column(name = "`url`")
    private String url;

    /**
     * 授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)
     */
    @Column(name = "`perms`")
    private String perms;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 菜单图标
     */
    @Column(name = "`icon`")
    private String icon;

    /**
     * 排序
     */
    @Column(name = "`order_num`")
    private Integer orderNum;

    // 非数据库字段
    @Transient
    private String parentName;
    // 非数据库字段
    @Transient
    private Integer level;
    // 非数据库字段
    @Transient
    private List<SysMenu> children;
}