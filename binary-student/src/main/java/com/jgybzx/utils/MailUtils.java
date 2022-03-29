package com.jgybzx.utils;

import com.jgybzx.config.MailConfig;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author Jgybz
 * @date 2022/3/25/0025 16:41
 * @des
 */
@Component
public class MailUtils {


    /**
     * 发送邮件
     *
     * @param to      接受人
     * @param subject 主题
     * @param content    发送内容
     * @throws MessagingException           异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendMail(String to, String subject, String content) throws MessagingException, UnsupportedEncodingException {

    }


}
