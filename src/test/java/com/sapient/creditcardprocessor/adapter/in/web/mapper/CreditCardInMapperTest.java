package com.sapient.creditcardprocessor.adapter.in.web.mapper;

import com.sapient.creditcardprocessor.adapter.in.web.resource.CreditCardRequest;
import com.sapient.creditcardprocessor.adapter.in.web.resource.CreditCardResponse;
import com.sapient.creditcardprocessor.domain.model.CreditCard;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CreditCardInMapperTest {

    @Test
    void shouldMapToDomainTest(){
        final CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setName("XYZ");
        creditCardRequest.setCreditLimit(0.0);
        creditCardRequest.setCardNumber("1358954993914435");
        Optional<CreditCard> creditCardOptional =CreditCardInMapper.mapToDomain(creditCardRequest);
        assertThat(creditCardOptional.isPresent());
        assertThat(creditCardOptional.get()).isNotNull();
        assertThat(creditCardOptional.get().getName()).isNotNull().isEqualTo(creditCardRequest.getName());
    }

    @Test
    void shouldMapToResourceTest(){
        final CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("1358954993914435");
        creditCard.setName("XYZ");
        creditCard.setCreditLimit(100.00);
        creditCard.setId(UUID.randomUUID());
        creditCard.setCreatedTime(Timestamp.from(Instant.now()));
        creditCard.setUpdatedTime(Timestamp.from(Instant.now()));
        Optional<CreditCardResponse> creditCardOptional =CreditCardInMapper.mapToResource(creditCard);
        assertThat(creditCardOptional.isPresent());
        assertThat(creditCardOptional.get()).isNotNull();
        assertThat(creditCardOptional.get().getName()).isNotNull().isEqualTo(creditCard.getName());
    }
}
