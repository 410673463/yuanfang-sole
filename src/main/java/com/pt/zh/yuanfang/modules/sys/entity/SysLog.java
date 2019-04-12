package com.pt.zh.yuanfang.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`sys_log`")
public class SysLog  implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "uid")
    private String uid;
    @Column(name = "ip")
    private String ip;
    @Column(name = "ajax")
    private Integer ajax;
    @Column(name = "uri")
    private String uri;
    @Column(name = "params")
    private String params;
    @Column(name = "httpMethod")
    private String httpMethod;
    @Column(name = "classMethod")
    private String classMethod;
    @Column(name = "actionName")
    private String actionName;
    @Column(name = "createDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

}