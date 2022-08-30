
package org.springblade.modules.user.tool.utils;


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class UploadUtil {



    //图片上传
    public  static String upload(MultipartFile file, String path, int width, int height) {

        if(file.isEmpty()){
            return "文件空空";
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String filePath = path;
        // 检测是否存在目录
         fileName = UUIDUtil.getId() +suffixName;
        File dest = new File(filePath + fileName);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            Thumbnails.of(new File(filePath + fileName)).size(width, height).toFile(filePath +width+"X"+height+"_"+ fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
    //多文件上传
    public static String handleFileUpload(HttpServletRequest request, String path) {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        // 文件上传后的路径
        String filePath = path;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath+file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => "
                            + e.getMessage();
                }
            } else {
                return "You failed to upload " + i
                        + " because the file was empty.";
            }
        }
        return "上传成功";

    }
}

