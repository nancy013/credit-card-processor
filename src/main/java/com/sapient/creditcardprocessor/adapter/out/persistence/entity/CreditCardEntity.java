package com.sapient.creditcardprocessor.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="credit_card_details")
public class CreditCardEntity implements Persistable<UUID> {
    @Id
    private UUID id;
    private String name;
    private String cardNumber;
    private Double creditLimit;
    private Timestamp createdTime;
    private Timestamp updatedTime;

    @Override
    public UUID getId() {
        return this.id;
    }

    public CreditCardEntity(String name, String cardNumber, Double creditLimit, Timestamp createdTime, Timestamp updatedTime) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    /**
     * Supports the creation of new credit card entity with a UUID id. On save
     * the framework uses the presence of an ID value to determine if the operation is a create or update
     * with null indicates a create or value present indicates the update
     * @return
     */
    @Override
    public boolean isNew() {
        boolean result = Objects.isNull(id);
        this.id = result ? UUID.randomUUID() : this.id;
        return false;
    }
}
