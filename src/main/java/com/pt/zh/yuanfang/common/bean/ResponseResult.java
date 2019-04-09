package com.pt.zh.yuanfang.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ApiModel(value = "请求结果响应体")
public class ResponseResult<T> implements Serializable {


    //@ApiModelProperty(value = "响应状态回执码")
    private Integer status;

    //@ApiModelProperty(value = "数据体")
    private T data;

    //@ApiModelProperty(value = "响应回执消息")
    private String msg;

    //@ApiModelProperty(value = "响应时间戳")
    private final long timestamps = System.currentTimeMillis();

    public synchronized static <T> ResponseResult<T> e(ResponseCode statusEnum) {
        return e(statusEnum,null);
    }

    public synchronized static <T> ResponseResult<T> e(ResponseCode statusEnum, T data) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setStatus(statusEnum.code);
        res.setMsg(statusEnum.msg);
        res.setData(data);
        return res;
    }


    private static final long serialVersionUID = 8992436576262574064L;

    public synchronized static <T>  ResponseResult error(String msg) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setMsg(msg);
        res.setStatus(-1);
        return  res;
    }
    public synchronized static <T>  ResponseResult OK(T data) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setStatus(ResponseCode.OK.code);
        res.setMsg(ResponseCode.OK.msg);
        res.setData(data);
        return  res;
    }
    public synchronized static <T>  ResponseResult OK() {
        ResponseResult<T> res = new ResponseResult<>();
        res.setStatus(ResponseCode.OK.code);
        res.setMsg(ResponseCode.OK.msg);
        return  res;
    }
    public synchronized static <T>  ResponseResult error(ResponseCode statusEnum) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setMsg(statusEnum.msg);
        res.setStatus(statusEnum.code);
        return  res;
    }
}
