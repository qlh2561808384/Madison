package com.longshao.madison.Utils.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
*   服务端：
*
* */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动");
        //创建socket 并给一个监听的端口号62000
        DatagramSocket ds = new DatagramSocket(62000);
        //由于socket编程 传送数据都是以字节形式传送的 所以这里定义byte数据接收客户端发送过来的数据
        byte[] bytes = new byte[512];
        //创建DatagramPacket 接收客户端的数据
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length);
        //接收客户端数据
        ds.receive(dp);

        //下面是接收到数据
        byte[] data = dp.getData();
        //发送者发送信息
        String clientMsg = new String(data, 0, data.length);
        //发送者ip
        String clientAddress = dp.getAddress().getHostAddress();
        //是发送数据长度
        int length = dp.getLength();
        //发送者端口号
        int port = dp.getPort();
        System.out.println("收到客户端的数据为=" + clientMsg + "客户端地址ip=" + clientAddress + "数据长度=" + length + "客户端发送端口号=" + port);

        //接受完数据后  回送给客户端
        String serverMsg = "回送给客户端";
        byte[] serverMsgBytes = serverMsg.getBytes();
        DatagramPacket clientDP = new DatagramPacket(serverMsgBytes, 0, serverMsgBytes.length, dp.getAddress(), dp.getPort());
        //回送给客户端
        ds.send(clientDP);
        ds.close();

    }
}
