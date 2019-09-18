package com.pt.zh.yuanfang.modules.sys.entity;

import com.pt.zh.yuanfang.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "`sys_role_menu`")
public class SysRoleMenu extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色ID
     */
    @Column(name = "`role_id`")
    private Integer roleId;

    /**
     * 菜单ID
     */
    @Column(name = "`menu_id`")
    private Integer menuId;



}