package com.longshao.madison.Utils;

import cn.hutool.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class excutePython {
    public static String getPythonRes(JSONObject object){
        String result = "";
        try {
            //设置命令行传入参数
//            JSONObject object = getBytes();
            String[] msg = new String[]{"python", "E:\\madison\\src\\main\\java\\com\\longshao\\madison\\Utils\\python\\aiJKKJ.py",object.toString()};
//            String msg = new String("python D:\\Users\\workspace\\learn-python\\com\\demo\\test1.py -b Apple -l type1=common-log,type2=advance-log -t startTime=1502770332956,endTime=1502856732956");
            //调用window的命令行窗口的方法
            Process pr = Runtime.getRuntime().exec(msg);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                //接收python数据
                result = line;
            }
            in.close();
            pr.waitFor();
//            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
