package com.jgybzx.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.ResponseHeaderOverrides;
import com.jgybzx.config.OssConfig;
import com.jgybzx.exception.BaseException;
import com.jgybzx.service.FileService;
import com.jgybzx.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.util.BuddhistCalendar;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jgybz
 * @date 2022/3/24/0024 16:26
 * des
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private OssConfig ossConfig;

    @Override
    public String uploadByTransferTo(String fileName, String tempFilePath) {
        OSS ossClient = ossConfig.createOssClient();
        String bucketName = ossConfig.getBucketName();
        // 用于在OSS上命名，建议格式 ：年月日/文件名.后缀名，此时可以 以时间建立一个文件夹保存上传的图片
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String transformDate = simpleDateFormat.format(new Date());
        String objectName = transformDate + "/" + System.currentTimeMillis() + "_" + fileName;
        // 设置设置 HTTP 头 里边的 Content-Type
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(OssConfig.getContentType(fileName.substring(fileName.lastIndexOf("."))));
        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(tempFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 以自定义的 HTTP头 格式上传
        ossClient.putObject(bucketName, objectName, inputStream, objectMetadata);

//        // 返回一个有时间的链接，（目前发现只能这样拿到返回连接，是否有其他方式待验证）
//        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
//
//        String url = ossClient.generatePresignedUrl(bucketName, objectName, expiration).toString();
//

//        // 截取返回的链接
//        return url.split("\\?")[0];
//        https://binary-stars.oss-cn-beijing.aliyuncs.com/2020-12-21/1608541742582_1.jpg
        // 关闭OSSClient。
        ossClient.shutdown();
        return "https://binary-stars.oss-cn-beijing.aliyuncs.com/" + objectName;
    }

    @Override
    public String uploadByInputStream(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)) {
            throw new BaseException("500", "文件名不能为空");
        }
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String prefix = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String bucketName = ossConfig.getBucketName();
        String filePath = DateUtil.getCurrentYear() + "/" + DateUtil.getCurrentMonth() + "/" + DateUtil.getCurrentDay() + "/";
        String objectName = filePath + originalFilename;
        OSS ossClient = ossConfig.createOssClient();
        int sameSize = 1;
        while (ossClient.doesObjectExist(bucketName, objectName)) {
            objectName = filePath + prefix + "(" + sameSize + ")" + "." + suffix;
            sameSize++;
        }
        // 设置设置 HTTP 头 里边的 Content-Type
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(OssConfig.getContentType(originalFilename.substring(originalFilename.lastIndexOf("."))));

        try {
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, objectName, inputStream, objectMetadata);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.err.println("Error Message:" + oe.getErrorMessage());
            System.err.println("Error Code:" + oe.getErrorCode());
            System.err.println("Request ID:" + oe.getRequestId());
            System.err.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.err.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.err.println("Error Message:" + ce.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return "https://binary-stars.oss-cn-beijing.aliyuncs.com/" + objectName;
    }


    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException {
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;
        String bucketName = ossConfig.getBucketName();
        OSS ossClient = ossConfig.createOssClient();
        OSSObject object = ossClient.getObject(bucketName, filePath);
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(filePath.getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
            bufferedInputStream = new BufferedInputStream(object.getObjectContent());
            byte[] bytes = new byte[1024];
            outputStream = response.getOutputStream();
            int read = 0;
            while ((read = bufferedInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            object.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ossClient.shutdown();
        }
    }


}
