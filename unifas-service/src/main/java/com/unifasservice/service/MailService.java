package com.unifasservice.service;

import com.unifasservice.dto.payload.response.DataMailResponse;

import javax.mail.MessagingException;

public interface MailService {
    void sendHtmlMail (DataMailResponse dataMailResponse, String templateName) throws MessagingException;
}
