package com.yg.j1902.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgResult {
    //状态码
    private Integer status;
    private String message;//短消息
    private Object data;//返回的对象

    public MsgResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public MsgResult() {
    }
}
