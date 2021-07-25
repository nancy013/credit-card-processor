package com.sapient.creditcardprocessor.adapter.out.persistence.mapper;

import com.sapient.creditcardprocessor.adapter.in.web.mapper.CreditCardInMapper;
import com.sapient.creditcardprocessor.adapter.in.web.resource.CreditCardRequest;
import com.sapient.creditcardprocessor.adapter.in.web.resource.CreditCardResponse;
import com.sapient.creditcardprocessor.adapter.out.persistence.entity.CreditCardEntity;
import com.sapient.creditcardprocessor.domain.model.CreditCard;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreditCardOutMapperTest {
    @Test
    void shouldMapToDomainTest(){
        final CreditCardEntity creditCardEntity = new CreditCardEntity();
        creditCardEntity.setId(UUID.randomUUID());
        creditCardEntity.setName("XYZ");
        creditCardEntity.setCreditLimit(0.0);
        creditCardEntity.setCardNumber("1358954993914435");
        Optional<CreditCard> creditCardOptional = CreditCardOutMapper.mapEntityToDomain(creditCardEntity);
        assertThat(creditCardOptional.isPresent());
        assertThat(creditCardOptional.get()).isNotNull();
        assertThat(creditCardOptional.get().getName()).isNotNull().isEqualTo(creditCardEntity.getName());
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
        Optional<CreditCardEntity> creditCardEntityOptional =CreditCardOutMapper.mapDomainToEntity(creditCard);
        assertThat(creditCardEntityOptional.isPresent());
        assertThat(creditCardEntityOptional.get()).isNotNull();
        assertThat(creditCardEntityOptional.get().getName()).isNotNull().isEqualTo(creditCard.getName());
    }
}
