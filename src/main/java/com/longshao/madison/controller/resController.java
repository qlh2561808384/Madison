package com.longshao.madison.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.longshao.madison.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class resController {
    @Autowired
    private UserInfo userInfo;
    @RequestMapping(value = "res",method = RequestMethod.POST)
    public UserInfo res(HttpServletRequest request) {
        userInfo = new UserInfo();
        return userInfo;
    }
    @RequestMapping("python")
    public void python() throws IOException {
        DatagramSocket ds = new DatagramSocket();
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

    @RequestMapping("getPython")
    public void getPython(@RequestBody Object obj){
        try {
            //需传入的参数
            String a = "1111";
//            System.out.println("start;;;" + a);
            //设置命令行传入参数
            System.out.println(obj.toString());
            String[] msg = new String[] { "python", "E:\\madison\\src\\main\\java\\com\\longshao\\madison\\Utils\\test.py",obj.toString()};
//            String msg = new String("python D:\\Users\\workspace\\learn-python\\com\\demo\\test1.py -b Apple -l type1=common-log,type2=advance-log -t startTime=1502770332956,endTime=1502856732956");

            Process pr = Runtime.getRuntime().exec(msg);

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
//            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
