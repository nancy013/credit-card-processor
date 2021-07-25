package com.sapient.creditcardprocessor.adapter.out.persistence.adapter;

import com.sapient.creditcardprocessor.adapter.out.persistence.mapper.CreditCardOutMapper;
import com.sapient.creditcardprocessor.adapter.out.persistence.repository.CreditCardRepository;
import com.sapient.creditcardprocessor.domain.model.CreditCard;
import com.sapient.creditcardprocessor.port.out.CreditCardOutPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreditCardPersistenceAdapter implements CreditCardOutPort {

    private CreditCardRepository creditCardRepository;
    public CreditCardPersistenceAdapter(final CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }
    @Override
    @Transactional
    public void save(final CreditCard creditCard) {
        creditCardRepository.save(CreditCardOutMapper.mapDomainToEntity(creditCard).get());
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<CreditCard>> findAll(int pageNumber, int pageSize) {
        return Optional.of(this.creditCardRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("updatedTime").descending()))
                .stream()
                .map(creditCardEntity -> CreditCardOutMapper.mapEntityToDomain(creditCardEntity).get())
                .collect(Collectors.toList()));
    }
}
