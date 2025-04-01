package com.ms.user.service;

import com.ms.user.model.UserModel;
import com.ms.user.producer.UserProducer;
import com.ms.user.repository.UserModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserModelRepository userModelRepository;
    private final UserProducer userProducer;

    public UserService(UserModelRepository userModelRepository, UserProducer userProducer) {
        this.userModelRepository = userModelRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        userModel = userModelRepository.save(userModel);
        userProducer.publicMessageEmail(userModel);
        return userModel;
    }
}