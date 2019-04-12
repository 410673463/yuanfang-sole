package com.pt.zh.yuanfang.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`sys_dict`")
public class SysDict  implements Serializable {
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
     * 备注信息
     */
    @Column(name = "`remarks`")
    private String remarks;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    @Column(name = "`del_flag`")
    private Byte delFlag;

}