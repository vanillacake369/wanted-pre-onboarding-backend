package com.wanted.onboarding.service;


import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.exception.UserNotFoundException;
import com.wanted.onboarding.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerNewUserAccount(User user) {
        // Existing User Validation
        List<User> savedUsers = userRepository.findMemberByEmail(user.getEmail());
        if(!savedUsers.isEmpty()){
            throw new UserNotFoundException(String.format("Employee already exist with given email: [%s]",user.getEmail()));
        }

        // Encryption
        User encryptedUser = getEncryptedUser(user);

        // Save to Repo
        return userRepository.save(encryptedUser);
    }

    protected User getEncryptedUser(User user) {
        User encryptedUser = User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        return encryptedUser;
    }
}
