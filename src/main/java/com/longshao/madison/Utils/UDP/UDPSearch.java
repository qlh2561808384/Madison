package com.longshao.madison.Utils.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
*   UDP 客户端 无需指定端口
* */
public class UDPSearch {
    public static void main(String[] args) throws IOException {
        System.out.println("search start......");
        //作为搜索方 无需指定端口
        DatagramSocket ds = new DatagramSocket();
        //要发送的数据
        String resquest = "hello qilonghui";
        byte[] responseBytes = resquest.getBytes();
        //将发送数据放到 报文中
        DatagramPacket datagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
        //设置ip  本地InetAddress.getLocalHost()
        datagramPacket.setAddress(InetAddress.getLocalHost());
        //设置端口号
        datagramPacket.setPort(2000);
        //发送给接收方报文
        ds.send(datagramPacket);

        byte[] bytes = new byte[512];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        //接收 提供方  回送回来的数据
        ds.receive(dp);

        //打印接收到的信息与发送者信息
        //发送者ip地址
        String ip = dp.getAddress().getHostAddress();
        int port = dp.getPort();
        int length = dp.getLength();
        String data = new String("search reciver from ip:" + ip + "port:" + port + "length:" + length);
        System.out.println(data);
        ds.close();

    }
}
