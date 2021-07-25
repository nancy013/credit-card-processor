package com.sapient.creditcardprocessor.service;

import com.sapient.creditcardprocessor.domain.model.CreditCard;
import com.sapient.creditcardprocessor.port.out.CreditCardOutPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreditCardService.class)
class CreditCardServiceTest {

    @MockBean
    private CreditCardOutPort creditCardOutPort;

    @Autowired
    private CreditCardService creditCardService;

    @Test
    @DisplayName("should save credit card details")
    void shouldSaveCreditCardDataTest(){
        doNothing().when(creditCardOutPort).save(any(CreditCard.class));
        CreditCard creditCard = mockCreditCard();
        creditCardService.save(creditCard);
        verify(creditCardOutPort, times(1)).save(creditCard);
    }

    @Test
    @DisplayName("should fetch credit cards")
    void shouldFetchCreditCardsTest(){
        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(mockCreditCard());
        when(creditCardOutPort.findAll(0,2)).thenReturn(Optional.of(creditCardList));
        Optional<List<CreditCard>> response = creditCardService.findAll(0,2);
        assertThat(response.isPresent());
        assertThat(response.get().get(0).getName()).isEqualTo("XYZ");
    }

    private CreditCard mockCreditCard(){
        final CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("1358954993914435");
        creditCard.setName("XYZ");
        creditCard.setCreditLimit(100.00);
        creditCard.setId(UUID.randomUUID());
        creditCard.setCreatedTime(Timestamp.from(Instant.now()));
        creditCard.setUpdatedTime(Timestamp.from(Instant.now()));
        return creditCard;
    }

}
