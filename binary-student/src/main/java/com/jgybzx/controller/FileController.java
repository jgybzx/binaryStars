package com.jgybzx.controller;


import com.jgybzx.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author jgybzx
 * @date 2020/12/21 10:28
 * 文件上传相关
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传 采用先保存在本地然后上传的方式
     *
     * @param file
     * @return java.lang.String
     * @author 郭江勇
     * @date 2022/3/24/0024 15:33
     */
    @PostMapping("/uploadByTransferTo")
    public String uploadByTransferTo(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传文件内容为空，请重新选择";
        }
        String tempFilePath = this.getClass().getResource("/").getPath();
        String fileName = file.getOriginalFilename();
        File tempFile = new File(tempFilePath + fileName);
        try {
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileService.uploadByTransferTo(fileName, tempFilePath + fileName);
    }

    /**
     * 文件流式上传
     *
     * @param file
     * @return java.lang.String
     * @author 郭江勇
     * @date 2022/3/25/0025 11:16
     */
    @PostMapping("/uploadByInputStream")
    public String uploadByInputStream(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "上传文件内容为空，请重新选择";
        }

        return fileService.uploadByInputStream(file);
    }

    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException {
        fileService.download(request,response,filePath);
    }

}
