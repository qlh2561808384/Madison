package com.longshao.madison.Utils.python;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class jpython {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);

        // 在同一行输入两个数字，用空格分开，作为传入Python代码的命令行参数
        System.out.println("Enter two integers(e.g. 12 34): ");
        String integers = input.nextLine();
        String[] numbers = integers.split(" ");

        // 定义传入Python脚本的命令行参数，将参数放入字符串数组里
        String cmds = String.format("python D://test.py %s %s",
                numbers[0], numbers[1]);

        // 执行CMD命令
        System.out.println("\nExecuting python script file now.");
        Process pcs = Runtime.getRuntime().exec(cmds);
        pcs.waitFor();

        // 定义Python脚本的返回值
        String result = null;
        // 获取CMD的返回流
        BufferedInputStream in = new BufferedInputStream(pcs.getInputStream());
        // 字符流转换字节流
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        // 这里也可以输出文本日志

        String lineStr = null;
        while ((lineStr = br.readLine()) != null) {
            result = lineStr;
        }
        // 关闭输入流
        br.close();
        in.close();

        System.out.println(result);

    }
}
