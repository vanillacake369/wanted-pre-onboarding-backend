package com.wanted.onboarding.auth;

import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


// 우리가 만든 Security Config에서 loginProcessingUrl("/login");
// login 요청 => 자동으로 UserDetailsService 타입으로 IoC 되어있는 빈의 loadUserByUsername 함수를 호출

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User savedUser = userRepository.findUserByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(String.format("[%s] of user not found " ,email))
        );

        // #1
        Set<GrantedAuthority> authorities = new HashSet<>();
        String roleName = savedUser.getRole().getRole();
        authorities.add(new SimpleGrantedAuthority(roleName));

        // LOG
        System.out.println("email : "+email);
        System.out.println("roleName : "+roleName);

        // #1 & #2
        return new org.springframework.security.core.userdetails.User(savedUser.getEmail(), savedUser.getPassword(),authorities);
        // return new PrincipalDetails(savedUser);
    }
}
