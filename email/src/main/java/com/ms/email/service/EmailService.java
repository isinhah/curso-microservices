package com.ms.email.service;

import com.ms.email.model.EmailModel;
import com.ms.email.repository.EmailModelRepository;
import org.eclipse.angus.mail.imap.AppendUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final EmailModelRepository emailModelRepository;
    private final JavaMailSender emailSender;

    public EmailService(EmailModelRepository emailModelRepository, JavaMailSender emailSender) {
        this.emailModelRepository = emailModelRepository;
        this.emailSender = emailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        try {
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(EmailModel.StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(EmailModel.StatusEmail.ERROR);
            throw e;
        }

        return emailModelRepository.save(emailModel);
    }
}
