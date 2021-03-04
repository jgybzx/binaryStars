package com.jgybzx.controller;


import com.jgybzx.config.OssConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author jgybzx
 * @date 2020/12/21 10:28
 * @description
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private OssConfig ossConfig;

    @PostMapping("/fileUpload.json")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
        if (file.isEmpty()) {
            return "上传文件内容为空，请重新选择";
        }
        String tempFilePath = this.getClass().getResource("/").getPath();
        String fileName = file.getOriginalFilename();
        File tempFile = new File(tempFilePath + fileName);
        try {
            file.transferTo(tempFile);
            //return "上传成功" + tempFilePath + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ossConfig.fileUpload(fileName, tempFilePath + fileName);
    }
}
