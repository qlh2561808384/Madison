package com.longshao.madison.Utils.UDP;

/*
*   局域网搜索案例
*   消息创建者
* */
public class MassageCreater {
    private static final String SN_HEADER = "收到暗号，我是（SN）：";
    private static final String PORT_HEARDER = "这是暗号，请回电（port）：";

    public static String buildWithPort(int port) {
        return PORT_HEARDER + port;
    }
    public static int parsePort(String data){
        if (data.startsWith(PORT_HEARDER)) {
            return Integer.parseInt(data.substring(PORT_HEARDER.length()));
        }
        return -1;
    }

    public static String buildWithSN(String sn) {
        return SN_HEADER + sn;
    }
    public static String parseSn(String data){
        if (data.startsWith(SN_HEADER)) {
            return data.substring(SN_HEADER.length());
        }
        return null;
    }
}
