package com.unifasservice.service;

import com.unifasservice.dto.response.DataMailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendHtmlMail (DataMailDTO dataMailDto, String templateName) throws MessagingException;
}
