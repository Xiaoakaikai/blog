package com.bkk.service;

import javax.mail.MessagingException;

public interface EmailService {


    void sendCode(String email) throws MessagingException;

    void friendPassSendEmail(String email);

    void friendFailedSendEmail(String email,String reason);

    void emailNoticeMe(String subject, String content);
}
