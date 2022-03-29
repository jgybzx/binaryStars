package com.jgybzx.service;

import com.jgybzx.entity.MailEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * @author Jgybz
 * @date 2022/3/25/0025 15:59
 * @des
 */
public interface MailService {
    void sendTxtMail(MailEntity mailEntity) throws MessagingException, UnsupportedEncodingException;

}
