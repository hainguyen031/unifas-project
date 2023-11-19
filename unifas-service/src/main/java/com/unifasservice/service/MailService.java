package com.unifasservice.service;

import com.unifasservice.dto.response.DataMailDto;

import javax.mail.MessagingException;

public interface MailService {
    void sendHtmlMail (DataMailDto dataMailDto, String templateName) throws MessagingException;
}
