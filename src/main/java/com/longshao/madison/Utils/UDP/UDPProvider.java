package com.longshao.madison.Utils.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*
 *   UDP 服务端  用户提供服务
 * */
public class UDPProvider {
    public static void main(String[] args) throws IOException {
        System.out.println("provider start........");
        //作为接收者  指定一个端口用于数据接收
        //监听 指定的端口
        DatagramSocket ds = new DatagramSocket(2000);
        //构建接收实体
        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);

        //接收
        ds.receive(receivePack);
        //打印接收到的信息与发送者信息
        //发送者ip地址
        String ip = receivePack.getAddress().getHostAddress();
        //发送者端口号
        int port = receivePack.getPort();
        //数据大小
        int length = receivePack.getLength();
        String data = new String(receivePack.getData(), 0, length);

        System.out.println("provider reciver from ip:" + ip + "prot:" + port + "length:" + data);

        //构建一份回送数据   回送给发送者
        String reponseData = "recoverLength：" + length;
        byte[] responseDataBytes = reponseData.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(responseDataBytes, responseDataBytes.length, receivePack.getAddress(), receivePack.getPort());
        //发送数据
        ds.send(datagramPacket);

        System.out.println("provider ends...");
        //关闭资源
        ds.close();
    }
}
