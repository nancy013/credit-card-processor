package com.sapient.creditcardprocessor.adapter.in.web.mapper;

import com.sapient.creditcardprocessor.adapter.in.web.resource.CreditCardRequest;
import com.sapient.creditcardprocessor.adapter.in.web.resource.CreditCardResponse;
import com.sapient.creditcardprocessor.domain.model.CreditCard;
import com.sapient.creditcardprocessor.util.EncryptionDecryptionUtility;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Mock;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

class CreditCardInMapperTest {

    @Test
    void shouldMapToDomainTest(){
        try (MockedStatic<EncryptionDecryptionUtility> utilities = Mockito.mockStatic(EncryptionDecryptionUtility.class)) {
            utilities.when(() -> EncryptionDecryptionUtility.encrypt("1358954993914435"))
                    .thenReturn("cSZ3ZKTTe85g7xY9M1PefI4zFAoKHBAmZG87zXq/30s=");
            final CreditCardRequest creditCardRequest = new CreditCardRequest();
            creditCardRequest.setName("XYZ");
            creditCardRequest.setCreditLimit(0.0);
            creditCardRequest.setCardNumber("1358954993914435");
            final Optional<CreditCard> creditCardOptional = CreditCardInMapper.mapToDomain(creditCardRequest);
            assertThat(creditCardOptional.isPresent());
            assertThat(creditCardOptional.get()).isNotNull();
            assertThat(creditCardOptional.get().getName()).isNotNull().isEqualTo(creditCardRequest.getName());
            assertThat(creditCardOptional.get().getCardNumber()).isNotNull().isEqualTo("cSZ3ZKTTe85g7xY9M1PefI4zFAoKHBAmZG87zXq/30s=");
        }
    }

    @Test
    void shouldMapToResourceTest(){
        try (MockedStatic<EncryptionDecryptionUtility> utilities = Mockito.mockStatic(EncryptionDecryptionUtility.class)) {
            utilities.when(() -> EncryptionDecryptionUtility.decrypt("cSZ3ZKTTe85g7xY9M1PefI4zFAoKHBAmZG87zXq/30s="))
                    .thenReturn("1358954993914435");
            final CreditCard creditCard = new CreditCard();
            creditCard.setCardNumber("cSZ3ZKTTe85g7xY9M1PefI4zFAoKHBAmZG87zXq/30s=");
            creditCard.setName("XYZ");
            creditCard.setCreditLimit(100.00);
            creditCard.setId(UUID.randomUUID());
            creditCard.setCreatedTime(Timestamp.from(Instant.now()));
            creditCard.setUpdatedTime(Timestamp.from(Instant.now()));
            final Optional<CreditCardResponse> creditCardOptional =CreditCardInMapper.mapToResource(creditCard);
            assertThat(creditCardOptional.isPresent());
            assertThat(creditCardOptional.get()).isNotNull();
            assertThat(creditCardOptional.get().getCardNumber()).isNotNull().isEqualTo("1358954993914435");
            assertThat(creditCardOptional.get().getName()).isNotNull().isEqualTo(creditCard.getName());
        }

    }
}
