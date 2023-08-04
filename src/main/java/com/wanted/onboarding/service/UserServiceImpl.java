package com.wanted.onboarding.service;


import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.exception.UserNotFoundException;
import com.wanted.onboarding.model.UserService;
import com.wanted.onboarding.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        List<User> savedUsers = userRepository.findMemberByEmail(user.getEmail());
        if(!savedUsers.isEmpty()){
            throw new UserNotFoundException(String.format("Employee already exist with given email: [%s]",user.getEmail()));
        }
        return userRepository.save(user);
    }
}
