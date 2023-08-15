package com.wanted.onboarding.dto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SignRequestTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Check if password has more than 8 chars")
    void checkMinLengthOfPassword(){
        // GIVEN
        String email = "hello@gmail.com";
        String password = "onetwo";

        // WHEN
        SignRequestDto unvaild = SignRequestDto.builder()
                .email(email)
                .password(password)
                .build();
        Set<ConstraintViolation<SignRequestDto>> violations = validator.validate(unvaild);

        // THEN
        for (ConstraintViolation<SignRequestDto> violation : violations) {
            System.err.println(violation.getMessage());
        }
    }
}
