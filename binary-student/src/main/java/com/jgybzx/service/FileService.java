package com.jgybzx.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author Jgybz
 * @date 2022/3/24/0024 16:25
 * des
 */
public interface FileService {
    /**
     * 保存本地的方式上传
     *
     * @param fileName
     * @param filePath
     * @return java.lang.String
     * @author 郭江勇
     * @date 2022/3/24/0024 16:34
     */
    String uploadByTransferTo(String fileName, String filePath);

    /**
     * 文件流方式上传
     *
     * @param file
     * @return java.lang.String
     * @author 郭江勇
     * @date 2022/3/25/0025 09:25
     */
    String uploadByInputStream(MultipartFile file) throws IOException;

    void download(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException;
}
