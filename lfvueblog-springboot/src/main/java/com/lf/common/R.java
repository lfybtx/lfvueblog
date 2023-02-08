package com.lf.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class R implements Serializable {
        // 200是正常，非200表示异常
        private int code;
        private String msg;
        private Object data;

        public static R success(Object data) {
                return success(200, "操作成功", data);
        }

        public static R success(int code, String msg, Object data) {
                R r = new R();
                r.setCode(code);
                r.setMsg(msg);
                r.setData(data);
                return r;
        }

        public static R error(String msg) {
                return error(400, msg, null);
        }

        public static R error(String msg, Object data) {
                return error(400, msg, data);
        }

        public static R error(int code, String msg, Object data) {
                R r = new R();
                r.setCode(code);
                r.setMsg(msg);
                r.setData(data);
                return r;
        }
}
