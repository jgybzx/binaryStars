package com.jgybzx.controller;

import com.jgybzx.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author jgybzx
 * @date 2020/9/8 14:57
 * @description
 */
@RestController
@RequestMapping("file")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUpload;

    @PostMapping("fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return fileUpload.fileUpload(file);
    }


    private static void remove1(List<String> list) {
        for (String s : list) {
            if ("3".equals(s)) {
                list.remove(s);
            }
        }
    }
}
