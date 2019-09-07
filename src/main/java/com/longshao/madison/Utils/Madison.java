package com.longshao.madison.Utils;

import cn.hutool.extra.mail.MailUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.python.util.PythonInterpreter;

public class Madison {
    public static void main(String[] args) {
        /*JSONObject object = JSONUtil.createObj();
        object.put("arg01", 1);
        object.put("arg02", 2);
        object.put("arg03", 3);
        byte[] bytes = JSONUtil.toJsonStr(object).getBytes();
        String s = new String(bytes,0, bytes.length);
        System.out.println(s);*/
      /*  int[] arr = new int[]{6, 2, 5, 4, 9, 1, 3, 0};
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
        for (int n = 0; n < arr.length; n++) {
            System.out.print(arr[n]);
        }*/
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("import sys ");
        interpreter.exec("a = input('Enter a:')");
        interpreter.exec("b = input('Enter b:')");
        interpreter.exec("print('%s * %s = %s' %(a, b, a*b))");
    }
    public static void swap(int[] arr, int i, int j) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int rever(int x){
        if(x>0){
            return x + rever(x - 1);
        }else {
            return 0;
        }
    }
}
