package com.sapient.creditcardprocessor.port.out;

import com.sapient.creditcardprocessor.domain.model.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardOutPort {
    void save(CreditCard creditCard);
    Optional<List<CreditCard>> findAll(int pageNumber, int pageSize);
}

