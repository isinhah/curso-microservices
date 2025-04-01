package com.ms.user.service;

import com.ms.user.model.UserModel;
import com.ms.user.repository.UserModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserModelRepository userModelRepository;

    public UserService(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        return userModelRepository.save(userModel);
    }
}