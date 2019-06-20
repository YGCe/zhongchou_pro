package com.yg.j1902.utils;

import com.yg.j1902.vo.MsgResult;

public class ResultFactory {

    //登录成功
    public static MsgResult buildSucessResult(String msg) {
        return buildMsgResult(MsgStatus.SUCESS, msg, null);
    }

    //登录失败
    public static MsgResult bulidFailResult(String msg) {

        return buildMsgResult(MsgStatus.FAIL, msg, null);
    }

    //提供创建方法
    public static MsgResult buildMsgResult(MsgStatus msgStatus, String msg, Object data) {
        return new MsgResult(msgStatus.code, msg, data);
    }

}
