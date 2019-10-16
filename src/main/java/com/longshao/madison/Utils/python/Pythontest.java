package com.longshao.madison.Utils.python;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.longshao.madison.Utils.excutePython;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Pythontest {
    public static void main(String[] args) {
    /*    try {
            //需传入的参数
            String a = "1111";
//            System.out.println("start;;;" + a);
            //设置命令行传入参数
            JSONObject object = getBytes();
            String[] msg = new String[]{"python", "E:\\madison\\src\\main\\java\\com\\longshao\\madison\\Utils\\python\\aiJKKJ.py",object.toString()};
//            String msg = new String("python D:\\Users\\workspace\\learn-python\\com\\demo\\test1.py -b Apple -l type1=common-log,type2=advance-log -t startTime=1502770332956,endTime=1502856732956");
            //调用window的命令行窗口的方法
            Process pr = Runtime.getRuntime().exec(msg);

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                //接收python数据
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
//            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        JSONObject object = getBytes();
        String pythonRes = excutePython.getPythonRes(object);
        JSONObject jsonObject = JSONUtil.parseObj(pythonRes);
        String result = jsonObject.get("result").toString();

        System.out.println(jsonObject);
        System.out.println(result);
    }
    public static JSONObject getBytes(){
        List list = new ArrayList();
        list.add("\"h\"");
        list.add("\"e\"");
        list.add("\"l\"");
        list.add("\"l\"");
        list.add("\"o\"");

        JSONObject jsonObject = JSONUtil.createObj();
        jsonObject.put("\"arg01\"", 1);
        jsonObject.put("\"patient_id\"", 2);
        jsonObject.put("\"detect_id\"", 3);
        jsonObject.put("\"list\"", list);
        jsonObject.put("\"username\"", "\"qlhclient\"");
        jsonObject.put("\"hearbeat\"", 4);
        /*byte[] bytes = JSONUtil.toJsonStr(jsonObject).getBytes();
        return bytes;*/
        return jsonObject;
    }
}
