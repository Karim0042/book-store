package com.company.bookstoreapp.service.impl;

import com.company.bookstoreapp.constants.MailConstant;
import com.company.bookstoreapp.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final MailConstant mailConstant;
    private final JavaMailSender javaMailSender;
    @Override
    public void sendNotification(String[] to, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom(mailConstant.getEmail());
        javaMailSender.send(simpleMailMessage);
    }
}
