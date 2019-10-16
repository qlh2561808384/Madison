package com.longshao.madison.controller;

import com.longshao.madison.Utils.IdGenertor;
import com.longshao.madison.Utils.Result;
import com.longshao.madison.Utils.UploadUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Major Function：<b>上传图片服务</b>
 */
@RestController
public class uploadImage {

    @RequestMapping("upload")
    public Result upload(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        Result msg = new Result();
        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
        for(MultipartFile file :files.values()){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
            if (file.isEmpty()) {
                // 设置错误状态码
                msg.setSuccess(false);
                msg.setContent("上传失败，请选择文件");                return msg;
            }
            // 拿到文件名
            String filename = file.getOriginalFilename();

            // 拿到文件名
            String newName = null;
            int one = filename.lastIndexOf(".");
            if (-1 == one) {
                msg.setSuccess(false);
                msg.setContent("上传失败,上传文件格式不对");
                return msg;
            }else {
                newName = filename.replace(filename.substring(0, one), uuid);
            }

            // 存放上传图片的文件夹
            File fileDir = UploadUtils.getImgDirFile(session);
            // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
//            System.out.println(fileDir.getAbsolutePath());
            try {
                // 构建真实的文件路径
                File newFile = new File(fileDir.getAbsolutePath() + File.separator + newName);
//                System.out.println(newFile.getAbsolutePath());

                // 上传图片到 -》 “绝对路径”
                file.transferTo(newFile);
                msg.setSuccess(true);
                msg.setContent(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }
    /*@RequestMapping("upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "\"qlh\":\"我是人\",\"hhh\":111";
        System.out.println("message form client");
        response.getWriter().write(json);
//        uploadImage(request, response);
    }*/
    // 上传图片文件
    private void uploadImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = "";
        try{
            DiskFileItemFactory dff = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(dff);
            List<FileItem> items = sfu.parseRequest((RequestContext) request);
            // 获取上传字段
            FileItem fileItem = items.get(0);
            // 更改文件名为唯一的
            String filename = fileItem.getName();
            if (filename != null) {
                filename = IdGenertor.generateGUID() + "." + FilenameUtils.getExtension(filename);
            }
            File storeDirectory = IdGenertor.getImgDirFile();

//            String path = genericPath(filename, storeDirectory);
            // 处理文件的上传
            try {
                fileItem.write(new File(storeDirectory, filename));

                String filePath = "/files/images" + "/" + filename;
                message = filePath;
            } catch (Exception e) {
                message = "upload failed";
            }
        } catch (Exception e) {
            message = "upload failed";
        } finally {
            response.getWriter().write(message);
        }
    }

    //计算文件的存放目录
    private String genericPath(String filename, String storeDirectory) {
        int hashCode = filename.hashCode();
        int dir1 = hashCode&0xf;
        int dir2 = (hashCode&0xf0)>>4;

        String dir = "/"+dir1+"/"+dir2;

        File file = new File(storeDirectory,dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }
}
