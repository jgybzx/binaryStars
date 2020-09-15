package com.jgybzx.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author jgybzx
 * @date 2020/9/8 15:00
 * @description
 */
public interface FileUploadService {
    String fileUpload(MultipartFile file) throws IOException;
}
