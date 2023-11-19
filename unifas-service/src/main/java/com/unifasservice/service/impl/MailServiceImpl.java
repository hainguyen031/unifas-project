package com.unifasservice.service.impl;

import com.unifasservice.dto.response.DataMailDto;
import com.unifasservice.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;


    private final ITemplateEngine templateEngine;
    @Override
    public void sendHtmlMail(DataMailDto dataMailDto, String templateName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        Context context = new Context();
        context.setVariables(dataMailDto.getProps());

        String html = templateEngine.process(templateName, context);

        helper.setTo(dataMailDto.getTo());
        helper.setSubject(dataMailDto.getSubject());
        helper.setText(html, true);

        mailSender.send(message);
    }
}
