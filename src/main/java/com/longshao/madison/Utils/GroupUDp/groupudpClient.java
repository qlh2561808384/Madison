package com.longshao.madison.Utils.GroupUDp;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class groupudpClient {
    //组播端口号
    private static final int port = 62000;
    //组播ip
    private static final String ip = "228.5.6.7";
    public static void main(String[] args) throws IOException, InterruptedException {
        client();
    }

    public static void client() throws IOException, InterruptedException {
        //获取组播地址
        InetAddress localHost = InetAddress.getByName(ip);
        //创建组播套接字
        MulticastSocket mcs = new MulticastSocket(port);
        //加入连接
        mcs.joinGroup(localHost);
        System.out.println("发送数据包启动，启动时间：" + DateUtil.now());
        while (true){

            //客户端发送数据
            /*String msg = "hello" + DateUtil.now();
            byte[] buf = msg.getBytes();*/
            byte[] buf = getBytes();
            DatagramPacket dp = new DatagramPacket(buf, 0, buf.length, localHost, port);
            mcs.send(dp);
            System.out.println("发送数据包给" + localHost + ":" + port);
            TimeUnit.SECONDS.sleep(1);

            //接收server端的数据
            byte[] buff = new byte[8192];
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
            byte[] data = datagramPacket.getData();
            String s = new String(data, 0, data.length);
            System.out.println("接收到来server端的回应数据" + s);
            mcs.receive(datagramPacket);


            //关闭资源
            mcs.leaveGroup(localHost);
            mcs.close();
        }
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
