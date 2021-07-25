package com.sapient.creditcardprocessor.adapter.out.persistence.adapter;

import com.sapient.creditcardprocessor.domain.model.CreditCard;
import com.sapient.creditcardprocessor.port.out.CreditCardOutPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class CreditCardPersistenceAdapter implements CreditCardOutPort {

    public CreditCardPersistenceAdapter() {
    }

    @Override
    public void save(final CreditCard creditCard) {

    }

    @Override
    public Optional<List<CreditCard>> findAll(int pageNumber, int pageSize) {
        return Optional.empty();
    }
}
