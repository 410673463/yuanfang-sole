package com.pt.zh.yuanfang.common.bean;

import lombok.Data;

@Data
public class InputSelect {
    /**
     * 显示的值,必须的
     */
    private String value;
    /**
     *携带数据
     */
    private Object data;

}
