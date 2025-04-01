package com.ms.user.producer;

import com.ms.user.dto.EmailDto;
import com.ms.user.model.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publicMessageEmail(UserModel userModel) {
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso");
        emailDto.setText(userModel.getName() + ", seja bem vindo(a)! \nNós agradecemos o seu cadastro, aproveite todos os recursos da nossa plataforma!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}