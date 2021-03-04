package com.jgybzx.service.impl;

import com.jgybzx.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jgybzx
 * @date 2020/9/8 15:00
 * @description
 */
@Service
public class FileUploadImpl implements FileUploadService {
    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @Override
    public String fileUpload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "图片为空";
        }
        // 文件原始名字
        String filename = file.getOriginalFilename();
        // 文件后缀
        String suffixName = filename.substring(filename.lastIndexOf('.'));
        // 获取存储文件的路径
        String filePath = this.getClass().getResource("").getPath() + "image/";
        // 获取当前时间，作为文件的名字
        String uploadFileName = String.valueOf(Calendar.getInstance().getTimeInMillis());
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String format = ft.format(date);
        String path = filePath + format + "/" + uploadFileName + suffixName;

        File upload = new File(path);
        // 判断目录是否存在
        File parentFile = upload.getParentFile();
        if (!parentFile.exists()) {
            upload.getParentFile().mkdirs();
        }
        file.transferTo(upload);

        return path;
    }
}
