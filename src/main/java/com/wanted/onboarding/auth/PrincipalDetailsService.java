package com.wanted.onboarding.auth;

import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;


// 우리가 만든 Security Config에서 loginProcessingUrl("/login");
// login 요청 => 자동으로 UserDetailsService 타입으로 IoC 되어있는 빈의 loadUserByUsername 함수를 호출

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("email : "+email);

        User savedUser = userRepository.findUserByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(String.format("[%s] of user not found " ,email))
        );

        // #1
        //  Set< GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(savedUser.getRole().getRole()));
        // #2
        //  Set<GrantedAuthority> authorities = user
        //                .getRoles()
        //                .stream()
        //                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        // #1 & #2
        // return new org.springframework.security.core.userdetails.User(savedUser.getEmail(), savedUser.getPassword(),authorities);

        return new PrincipalDetails(savedUser);
    }
}
