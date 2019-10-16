package com.longshao.madison.Utils;

import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

public class UploadUtils {
    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String IMG_PATH_PREFIX = "static/upload/image";

    /*
    *   创建或寻找项目下面的某个文件夹  没有则创建
    * */
    public static File getImgDirFile(HttpSession session){

        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("src/main/resources/" + IMG_PATH_PREFIX + "/" + "Android");

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

    /**
     *
     * @param file
     * @return
     *  判断文件类型一般可采用两种方式
     * 后缀名判断
     * 简单易操作，但无法准确判断类型
     * 文件头信息判断
     * 通常可以判断文件类型，但有些文件类型无法判断（如word和excel头信息的前几个字节是一样的，无法判断）
     * 使用apache.tika可轻松解决以上两种方式存在的问题
     */
    public static String getMimeType(File file) {
        if (file.isDirectory()) {
            return "the target is a directory";
        }
        AutoDetectParser parser = new AutoDetectParser();
        parser.setParsers(new HashMap<MediaType, Parser>());

        Metadata metadata = new Metadata();
        metadata.add(TikaMetadataKeys.RESOURCE_NAME_KEY, file.getName());

        InputStream stream;
        try {
            stream = new FileInputStream(file);
            parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metadata.get(HttpHeaders.CONTENT_TYPE);
    }
}
