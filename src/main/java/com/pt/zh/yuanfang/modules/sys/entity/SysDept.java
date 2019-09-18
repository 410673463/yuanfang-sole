package com.pt.zh.yuanfang.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pt.zh.yuanfang.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Table(name = "`sys_dept`")
@Data
public class SysDept extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机构名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 上级机构ID，一级机构为0
     */
    @Column(name = "`parent_id`")
    private Integer parentId;

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
    @Transient
    private SysDept parentDept;
    @Transient
    private List<SysDept> children;
}