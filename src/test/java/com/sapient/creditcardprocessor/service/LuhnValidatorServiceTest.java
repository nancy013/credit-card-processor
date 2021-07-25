package com.sapient.creditcardprocessor.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LuhnValidatorService.class)
class LuhnValidatorServiceTest {
    @Autowired
    private LuhnValidatorService luhnValidatorService;

    @Test
    @DisplayName("should return true as credit card number is Valid")
    void shouldCheckIfValidCreditCardNumberTest() {
        assertThat(luhnValidatorService.isValidCreditCardNumber("1358954993914435")).isTrue();
    }

    @Test
    @DisplayName("should return false as credit card number is invalid")
    void shouldCheckIfInValidCreditCardNumberTest() {
        assertThat(luhnValidatorService.isValidCreditCardNumber("501459939399")).isFalse();
    }
}
