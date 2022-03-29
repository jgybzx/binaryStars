package com.jgybzx.service.impl;

import com.jgybzx.config.MailConfig;
import com.jgybzx.entity.Mail;
import com.jgybzx.entity.MailEntity;
import com.jgybzx.mappers.MailMapper;
import com.jgybzx.service.MailService;
import com.jgybzx.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author Jgybz
 * @date 2022/3/25/0025 15:59
 * @des
 */
@Service
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String from;

    @Value("${mail.sender.to}")
    private String to;

    @Autowired
    private  MailMapper mailMapper;
    @Override
    public void sendTxtMail(MailEntity mailEntity) throws MessagingException, UnsupportedEncodingException {
        MailConfig.init(mailMapper.query());
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject(mailEntity.getSubject());
//        message.setText(mailEntity.getContent());
//        mailSender.send(message);
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(MailConfig.host);
        sender.setPort(MailConfig.port);
        sender.setUsername(MailConfig.userName);
        sender.setPassword(MailConfig.passWord);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", MailConfig.timeout);
        p.setProperty("mail.smtp.auth", "false");
        p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sender.setJavaMailProperties(p);
        MimeMessage mimeMessage = sender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom("guojy_6526@163.com", "PERSONAL");
        messageHelper.setTo(to);
        messageHelper.setSubject(mailEntity.getSubject());
        messageHelper.setText(mailEntity.getContent());
        sender.send(mimeMessage);
    }
}
