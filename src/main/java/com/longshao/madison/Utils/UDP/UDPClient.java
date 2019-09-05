package com.longshao.madison.Utils.UDP;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/*
*   UDP客户端
* */
public class UDPClient {
    private static final String target_id = "127.0.0.1";
    private static final int target_prot = 62000;
    public static void main(String[] args) throws IOException {
        System.out.println("客户端启动");
        DatagramSocket ds = new DatagramSocket();
//        String msg = "你好，我是客户端";
//        byte[] bytes = msg.getBytes();
        byte[] bytes = getBytes();
        DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length);
        dp.setPort(62000);
        dp.setAddress(InetAddress.getLocalHost());
        //发送数据给服务器
        ds.send(dp);


        //接收服务器回送过来的数据
        byte[] serverBytes = new byte[512];
        DatagramPacket serverDP = new DatagramPacket(serverBytes, serverBytes.length);
        //接收来自服务器的数据
        ds.receive(serverDP);

        byte[] data = serverDP.getData();
        String clientMsg = new String(data, 0, data.length);
        String clientAddress = serverDP.getAddress().getHostAddress();
        int length = serverDP.getLength();
        int port = serverDP.getPort();
        System.out.println("收到服务端的数据为=" + clientMsg + "服务端地址ip=" + clientAddress + "数据长度=" + length + "服务端发送端口号=" + port);

        ds.close();
    }

    public static byte[] getBytes(){
        List list = new ArrayList();
        list.add("h");
        list.add("e");
        list.add("l");
        list.add("l");
        list.add("o");
        JSONObject jsonObject = JSONUtil.createObj();
        jsonObject.put("arg01", 1);
        jsonObject.put("patient_id", 2);
        jsonObject.put("detect_id", 3);
        jsonObject.put("list", list);
        jsonObject.put("username", "qlhclient");
        jsonObject.put("hearbeat", 4);
        byte[] bytes = JSONUtil.toJsonStr(jsonObject).getBytes();
        return bytes;
    }
}
