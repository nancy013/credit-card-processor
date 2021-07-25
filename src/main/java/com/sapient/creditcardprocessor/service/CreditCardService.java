package com.sapient.creditcardprocessor.service;

import com.sapient.creditcardprocessor.domain.model.CreditCard;
import com.sapient.creditcardprocessor.port.in.CreditCardInPort;
import com.sapient.creditcardprocessor.port.out.CreditCardOutPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService implements CreditCardInPort {
    private CreditCardOutPort creditCardOutPort;

    public CreditCardService(CreditCardOutPort creditCardOutPort) {
        this.creditCardOutPort = creditCardOutPort;
    }

    @Override
    public void save(CreditCard creditCard) {
        this.creditCardOutPort.save(creditCard);
    }

    @Override
    public Optional<List<CreditCard>> findAll(int pageNumber, int pageSize) {
        return this.creditCardOutPort.findAll(pageNumber,pageSize);
    }
}
