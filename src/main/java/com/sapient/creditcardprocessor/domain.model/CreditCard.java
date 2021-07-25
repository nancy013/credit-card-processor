package com.sapient.creditcardprocessor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    private UUID id;
    private String name;
    private String cardNumber;
    private Double creditLimit;
    private Timestamp createdTime;
    private Timestamp updatedTime;
}
