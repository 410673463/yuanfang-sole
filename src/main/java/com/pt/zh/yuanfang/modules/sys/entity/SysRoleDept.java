package com.pt.zh.yuanfang.modules.sys.entity;

import com.pt.zh.yuanfang.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "`sys_role_dept`")
public class SysRoleDept extends BaseEntity implements Serializable {
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
     * 机构ID
     */
    @Column(name = "`dept_id`")
    private Integer deptId;


}