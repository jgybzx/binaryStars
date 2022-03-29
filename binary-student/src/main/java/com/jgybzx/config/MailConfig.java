package com.jgybzx.config;

import com.jgybzx.entity.Mail;
import com.jgybzx.mappers.MailMapper;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author Jgybz
 * @date 2022/3/25/0025 16:38
 * @des
 */
public class MailConfig {
    //    private static final String PROPERTIES_DEFAULT = "mailConfig_pop3.properties";
    public static String host;
    public static Integer port;
    public static String userName;
    public static String passWord;
    public static String emailForm;
    public static String timeout;
    public static String personal;
    public static Properties properties;


    public static void init(Mail mail) {
        properties = new Properties();
        String propertiesFile = "";
        try {

            if ("IMAP/SMTP".equals(mail.getMailProtocol())) {
                propertiesFile = "config/mailConfig_pop3.properties";
            } else {
                propertiesFile = "config/mailConfig_imap.properties";
            }
            InputStream inputStream = MailConfig.class.getClassLoader().getResourceAsStream(propertiesFile);
            properties.load(inputStream);
            inputStream.close();

            host = properties.getProperty("mailHost");
            port = Integer.parseInt(properties.getProperty("mailPort"));
            userName = properties.getProperty("mailUsername");
            passWord = properties.getProperty("mailPassword");
            emailForm = properties.getProperty("mailFrom");
            timeout = properties.getProperty("mailTimeout");
            personal = "将光阴比作夏";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
