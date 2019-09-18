package com.pt.zh.yuanfang.modules.sys.entity;

import com.pt.zh.yuanfang.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "`sys_dict`")
public class SysDict extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 数据值
     */
    @Column(name = "`value`")
    private String value;

    /**
     * 标签名
     */
    @Column(name = "`label`")
    private String label;

    /**
     * 类型
     */
    @Column(name = "`type`")
    private String type;

    /**
     * 描述
     */
    @Column(name = "`description`")
    private String description;

    /**
     * 排序（升序）
     */
    @Column(name = "`sort`")
    private Integer sort;

    /**
     * 备注信息
     */
    @Column(name = "`remarks`")
    private String remarks;


}