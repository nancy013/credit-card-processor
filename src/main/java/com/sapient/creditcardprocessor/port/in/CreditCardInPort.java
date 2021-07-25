package com.sapient.creditcardprocessor.port.in;

import com.sapient.creditcardprocessor.domain.model.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardInPort {
    void save(CreditCard creditCard);
    Optional<List<CreditCard>> findAll(int pageNumber, int pageSize);
}
