package com.sapient.creditcardprocessor.adapter.in.web.mapper;

import com.sapient.creditcardprocessor.adapter.in.web.resource.CreditCardRequest;
import com.sapient.creditcardprocessor.adapter.in.web.resource.CreditCardResponse;
import com.sapient.creditcardprocessor.domain.model.CreditCard;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

public class CreditCardInMapper {

    public static Optional<CreditCard> mapToDomain(final CreditCardRequest creditCardRequest){
        final CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(creditCardRequest.getCardNumber());
        creditCard.setCreatedTime(Timestamp.from(Instant.now()));
        creditCard.setName(creditCardRequest.getName());
        creditCard.setCreditLimit(creditCardRequest.getCreditLimit());
        creditCard.setUpdatedTime(creditCard.getCreatedTime());
        return Optional.of(creditCard);
    }

    public static Optional<CreditCardResponse> mapToResource(CreditCard creditCard){
        CreditCardResponse creditCardResponse = new CreditCardResponse();
        creditCardResponse.setId(creditCard.getId());
        creditCardResponse.setName(creditCard.getName());
        creditCardResponse.setCardNumber(creditCard.getCardNumber());
        creditCardResponse.setCreditLimit(creditCard.getCreditLimit());
        creditCardResponse.setCreatedTime(creditCard.getCreatedTime());
        creditCardResponse.setUpdatedTime(creditCard.getUpdatedTime());
        return Optional.of(creditCardResponse);
    }

}
