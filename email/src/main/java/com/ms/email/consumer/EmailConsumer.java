package com.ms.email.consumer;

import com.ms.email.dto.EmailRecordDto;
import com.ms.email.model.EmailModel;
import com.ms.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDto dto) {
        // Conversão do DTO para Entidade
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(dto, emailModel);
        // Conexão do SMTP para o Gmail
        emailService.sendEmail(emailModel);
    }
}