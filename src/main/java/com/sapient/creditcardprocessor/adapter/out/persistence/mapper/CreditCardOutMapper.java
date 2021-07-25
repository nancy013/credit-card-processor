package com.sapient.creditcardprocessor.adapter.out.persistence.mapper;

import com.sapient.creditcardprocessor.adapter.out.persistence.entity.CreditCardEntity;
import com.sapient.creditcardprocessor.domain.model.CreditCard;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreditCardOutMapper {

    public static Optional<CreditCardEntity> mapDomainToEntity(final CreditCard creditCard){
        final CreditCardEntity creditCardEntity = new CreditCardEntity();
        creditCardEntity.setName(creditCard.getName());
        creditCardEntity.setCardNumber(creditCard.getCardNumber());
        creditCardEntity.setCreditLimit(creditCard.getCreditLimit());
        creditCardEntity.setCreatedTime(creditCard.getCreatedTime());
        creditCardEntity.setUpdatedTime(creditCard.getUpdatedTime());
        return  Optional.of(creditCardEntity);
    }

    public static Optional<CreditCard> mapEntityToDomain(final CreditCardEntity creditCardEntity){
        final CreditCard creditCard = new CreditCard();
        creditCard.setId(creditCardEntity.getId());
        creditCard.setName(creditCardEntity.getName());
        creditCard.setCardNumber(creditCardEntity.getCardNumber());
        creditCard.setCreditLimit(creditCardEntity.getCreditLimit());
        creditCard.setCreatedTime(creditCardEntity.getCreatedTime());
        creditCard.setUpdatedTime(creditCardEntity.getUpdatedTime());
        return  Optional.of(creditCard);
    }
}
