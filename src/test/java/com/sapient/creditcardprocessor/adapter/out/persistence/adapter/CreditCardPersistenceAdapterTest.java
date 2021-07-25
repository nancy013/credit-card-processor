package com.sapient.creditcardprocessor.adapter.out.persistence.adapter;

import com.sapient.creditcardprocessor.adapter.out.persistence.entity.CreditCardEntity;
import com.sapient.creditcardprocessor.adapter.out.persistence.repository.CreditCardRepository;
import com.sapient.creditcardprocessor.domain.model.CreditCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreditCardPersistenceAdapter.class)
public class CreditCardPersistenceAdapterTest {
    @MockBean
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CreditCardPersistenceAdapter creditCardPersistenceAdapter;

    @DisplayName("should save credit card information into DB")
    @Test
    void shoudSaveCreditCardTest(){
        CreditCard creditCard = mockCreditCard();
        when(creditCardRepository.save(any(CreditCardEntity.class))).thenReturn(mockedResponse());
        creditCardPersistenceAdapter.save(creditCard);
        verify(creditCardRepository, times(1)).save(any(CreditCardEntity.class));
    }

    @Test
    @DisplayName("should fetch the credit cards from DB")
    void shouldFetchCreditCardsTest(){
        List<CreditCardEntity> creditCardEntityList = new ArrayList<>();
        creditCardEntityList.add(mockedResponse());
        Page<CreditCardEntity> pagedTasks = new PageImpl(creditCardEntityList);
        when(creditCardRepository.findAll(any(PageRequest.class))).thenReturn(pagedTasks);
        Optional<List<CreditCard>> response = creditCardPersistenceAdapter.findAll(0,3);
        assertThat(response.isPresent());
        assertThat(response.get().get(0).getName()).isEqualTo("XYX");
    }

    private CreditCardEntity mockedResponse(){
        return new CreditCardEntity("XYX","1234567890",122.22,Timestamp.from(Instant.now()),Timestamp.from(Instant.now()));
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
