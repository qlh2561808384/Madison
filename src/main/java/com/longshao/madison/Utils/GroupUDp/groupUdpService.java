package com.longshao.madison.Utils.GroupUDp;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class groupUdpService {

    //组播端口号
    private static final int port = 62000;
    //组播ip
    private static final String ip = "228.5.6.7";

    public static void main(String[] args) throws IOException, InterruptedException {
        server();
    }
    //接收端
    public static void server() throws IOException, InterruptedException {
        //获取组播地址
        InetAddress localHost = InetAddress.getByName(ip);
        //创建组播套接字
        MulticastSocket mcs = new MulticastSocket(port);
        //加入连接
        mcs.joinGroup(localHost);
        //服务端接收来世客户端的数据
        byte[] buffer = new byte[8192];
        System.out.println("接收数据包启动，启动时间" + DateUtil.now());
        while (true){
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            mcs.receive(dp);

            //获取数据
            byte[] data = dp.getData();
            String client = new String(data, 0, data.length);
            System.out.println("接收到来至客户端的数据"+client);

            //回送发送端数据
            //发送端地址
            byte[] serverDataByte = getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(serverDataByte, serverDataByte.length);
            mcs.send(datagramPacket);
            TimeUnit.SECONDS.sleep(1);
            //关闭资源
            mcs.leaveGroup(localHost);
            mcs.close();
        }
    }
    public static byte[] getBytes(){
        List list = new ArrayList();
        list.add("w");
        list.add("o");
        list.add("o");
        list.add("r");
        list.add("d");
        JSONObject jsonObject = JSONUtil.createObj();
        jsonObject.put("arg01", 11);
        jsonObject.put("patient_id", 22);
        jsonObject.put("detect_id", 33);
        jsonObject.put("list", list);
        jsonObject.put("username", "qlhserver");
        jsonObject.put("hearbeat", 44);
        byte[] bytes = JSONUtil.toJsonStr(jsonObject).getBytes();
        return bytes;
    }
}
