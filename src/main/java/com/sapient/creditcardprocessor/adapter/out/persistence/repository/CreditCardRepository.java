package com.sapient.creditcardprocessor.adapter.out.persistence.repository;

import com.sapient.creditcardprocessor.adapter.out.persistence.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, UUID> {
}
