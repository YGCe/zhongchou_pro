package com.yg.j1902.utils;

public enum MsgStatus {
    SUCESS(200),FAIL(400),NOT_FOUND(404);
    public int code;
    MsgStatus(int code) {
        this.code = code;
    }
}
