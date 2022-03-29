package com.jgybzx.controller;

import com.jgybzx.entity.MailEntity;
import com.jgybzx.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * @author Jgybz
 * @date 2022/3/25/0025 15:30
 * @des
 */
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/sendTxtMail")
    public void sendTxtMail(@RequestBody MailEntity mailEntity) throws MessagingException, UnsupportedEncodingException {
        mailService.sendTxtMail(mailEntity);
    }


}
