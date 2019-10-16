package com.longshao.madison.Utils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Major Function：<b>ID生成 工具类</b>
 */

public class IdGenertor {
    /**
     * 生成UUID
     *
     * @return UUID
     */
    public static String generateGUID() {
        return new BigInteger(165, new Random()).toString(36).toUpperCase();
    }

    public static String generateOrdersNum() {
        //YYYYMMDD+当前时间（纳秒）  ：   1秒=1000毫秒=1000*1000纳秒
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String str = df.format(now);
        return str+System.nanoTime();
    }

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String IMG_PATH_PREFIX = "static/upload/image";

    /*
     *   创建或寻找项目下面的某个文件夹  没有则创建
     * */
    public static File getImgDirFile(){

        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("src/main/resources/" + IMG_PATH_PREFIX + "/");

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }
    /*
     *  获取某个路径下面的文件，返回文件的绝对路径
     * relativePath:项目跟路径下的目录  --springboot static 目录相当于根路径（SpringBoot 默认）
     * */
    public static File getFileDirPath(String relativePath){
        String filePath = new String("src/main/resources/" + relativePath);
        File file = new File(filePath);
        return file;
    }

}
