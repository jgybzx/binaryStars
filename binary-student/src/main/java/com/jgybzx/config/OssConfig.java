package com.jgybzx.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.jgybzx.utils.enums.FileSuffixEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jgybzx
 * @date 2020/12/21 15:40
 * @description 用于连接阿里 OSS 对象存储的必要条件
 * 注意点1、设置bucket 读写权限为公共读
 * 2、如果是使用新建的RAM进行操作，注意设置 Bucket 授权策略
 * https://blog.csdn.net/JGYBZX_G/article/details/111480067
 */
@Component
@ConfigurationProperties(prefix = "oss")
public class OssConfig {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * @param fileName     文件上传时的名字
     * @param tempFilePath 文件保存到本地时的临时目录，用于生产文件流
     * @return
     * @throws FileNotFoundException
     */
    public String fileUpload(String fileName, String tempFilePath) throws FileNotFoundException {
        // 用于在OSS上命名，建议格式 ：年月日/文件名.后缀名，此时可以 以时间建立一个文件夹保存上传的图片
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String transformDate = simpleDateFormat.format(new Date());
        String objectName = transformDate + "/" + System.currentTimeMillis() + "_" + fileName;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 设置设置 HTTP 头 里边的 Content-Type
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
        // 上传文件流。
        InputStream inputStream = new FileInputStream(tempFilePath);
        //ossClient.putObject(bucketName, objectName, inputStream);

        // 以自定义的 HTTP头 格式上传
        ossClient.putObject(bucketName, objectName, inputStream, objectMetadata);

        // 返回一个有时间的链接，（目前发现只能这样拿到返回连接，是否有其他方式待验证）
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        String url = ossClient.generatePresignedUrl(bucketName, objectName, expiration).toString();

        // 关闭OSSClient。
        ossClient.shutdown();
        // 截取返回的链接
        return url.split("\\?")[0];
    }

    /**
     * 解决问题，直接访问上传的图片地址，会让下载而不是直接访问
     * 设置设置 HTTP 头 里边的 Content-Type
     * txt 格式经过测试，不需要转换 上传之后就是 text/plain。其他未测试
     * 已知  如果 Content-Type = .jpeg 访问地址会直接下载，本方法也是解决此问题
     *
     * @param FilenameExtension
     * @return
     */
    public static String getContentType(String FilenameExtension) {
        if (FileSuffixEnum.BMP.getCode().equalsIgnoreCase(FilenameExtension)) {
            return "image/bmp";
        }
        if (FileSuffixEnum.GIF.getCode().equalsIgnoreCase(FilenameExtension)) {
            return "image/gif";
        }
        if (FileSuffixEnum.JPEG.getCode().equalsIgnoreCase(FilenameExtension) ||
                ".jpg".equalsIgnoreCase(FilenameExtension) ||
                ".png".equalsIgnoreCase(FilenameExtension)) {
            return "image/jpg";
        }
        if (FileSuffixEnum.HTML.getCode().equalsIgnoreCase(FilenameExtension)) {
            return "text/html";
        }

        if (FileSuffixEnum.TXT.getCode().equalsIgnoreCase(FilenameExtension)) {
            return "text/plain";
        }
        if (FileSuffixEnum.VSD.getCode().equalsIgnoreCase(FilenameExtension)) {
            return "application/vnd.visio";
        }
        if (FileSuffixEnum.PPTX.getCode().equalsIgnoreCase(FilenameExtension) ||
                FileSuffixEnum.PPT.getCode().equalsIgnoreCase(FilenameExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (FileSuffixEnum.DOCX.getCode().equalsIgnoreCase(FilenameExtension) ||
                FileSuffixEnum.DOC.getCode().equalsIgnoreCase(FilenameExtension)) {
            return "application/msword";
        }
        if (FileSuffixEnum.XML.getCode().equalsIgnoreCase(FilenameExtension)) {
            return "text/xml";
        }
        return "image/jpg";
    }


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

}
