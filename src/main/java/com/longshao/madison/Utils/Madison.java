package com.longshao.madison.Utils;

import cn.hutool.extra.mail.MailUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class Madison {
    public static void main(String[] args) {
        JSONObject object = JSONUtil.createObj();
        object.put("arg01", 1);
        object.put("arg02", 2);
        object.put("arg03", 3);
        byte[] bytes = JSONUtil.toJsonStr(object).getBytes();
        String s = new String(bytes,0, bytes.length);
        System.out.println(s);
    }
}
