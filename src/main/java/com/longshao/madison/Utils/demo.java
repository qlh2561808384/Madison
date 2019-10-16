package com.longshao.madison.Utils;
public class demo {
    public static void main(String[] args) {
 /*       String str = "picture.jpg";
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        String s = str.replaceAll(str, uuid + ".jpg");
        System.out.println(s);*/
        String str = "pic.tu.re.png";
        String str1 = "picture.jpeg";
        String str2 = "picture.jpg";
        String s = conver(str);
        String s1 = conver(str1);
        String s2 = conver(str2);
        System.out.println(s + "==" + s1 + "==" + s2);
    }

    public static String conver(String string) {

        int one = string.lastIndexOf(".");
        String replace = string.replace(string.substring(0, one), "12s2dadad5adsas2d2a");
        return replace;
    }


}
