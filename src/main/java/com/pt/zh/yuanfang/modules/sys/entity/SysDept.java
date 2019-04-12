package com.pt.zh.yuanfang.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Table(name = "`sys_dept`")
@Data
public class SysDept  implements Serializable {
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