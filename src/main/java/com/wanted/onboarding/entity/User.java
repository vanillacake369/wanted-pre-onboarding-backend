package com.wanted.onboarding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.regex.Pattern;

@Getter // getter 메소드 생성
@Builder // 빌더를 사용할 수 있게 함
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @Column(nullable = false)
    @Length(min = 8)
    private String password;
}
