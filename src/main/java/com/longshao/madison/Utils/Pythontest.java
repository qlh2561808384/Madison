package com.longshao.madison.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Pythontest {
    public static void main(String[] args) {
        try {
            //需传入的参数
            String a = "1111";
            System.out.println("start;;;" + a);
            //设置命令行传入参数
            String[] msg = new String[] { "python", "E:\\madison\\src\\main\\java\\com\\longshao\\madison\\Utils\\test.py"};
            Process pr = Runtime.getRuntime().exec(msg);

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
