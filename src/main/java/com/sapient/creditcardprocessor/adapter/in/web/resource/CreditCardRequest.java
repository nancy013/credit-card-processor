package com.sapient.creditcardprocessor.adapter.in.web.resource;

import com.sapient.creditcardprocessor.validator.ValidCreditNumber;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Data
public class CreditCardRequest {

    @Parameter(
            description = "Name for credit card holder.",
            required = true)
    @NotBlank
    private String name;

    @Parameter(
            description = "Credit Card Number upto 19 digits.",
            required = true)
    @ValidCreditNumber
    private String cardNumber;

    @Parameter(
            description = "Credit Card creditLimit. Default 0. ")
    @DecimalMin(value = "0.00")
    private Double creditLimit;
}
