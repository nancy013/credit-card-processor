package com.sapient.creditcardprocessor.adapter.in.web.controller;

import com.sapient.creditcardprocessor.adapter.in.web.mapper.CreditCardInMapper;
import com.sapient.creditcardprocessor.adapter.in.web.resource.CreditCardRequest;
import com.sapient.creditcardprocessor.adapter.in.web.resource.CreditCardResponse;
import com.sapient.creditcardprocessor.adapter.in.web.resource.ErrorResponse;
import com.sapient.creditcardprocessor.port.in.CreditCardInPort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/creditcards")
public class CreditCardController {
    private CreditCardInPort creditCardInPort;

    public CreditCardController(final CreditCardInPort creditCardInPort) {
        this.creditCardInPort = creditCardInPort;
    }

    @ApiOperation(
            value = "addCreditCard API: Save credit card information for the user.",
            response = CreditCardResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request",
                    response = ErrorResponse.class),
            @ApiResponse(code = 422, message = "Unprocessable Entity",
                    response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error",
                    response = ErrorResponse.class),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCreditCard(@RequestBody @Valid CreditCardRequest creditCardRequest){
        this.creditCardInPort.save(CreditCardInMapper.mapToDomain(creditCardRequest).get());
    }

   @ApiOperation(value = "getCreditCards API: get all the Credit Cards.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCardResponse> getCreditCards(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size){
        return this.creditCardInPort
                .findAll(page,size)
                .get()
                .stream()
                .map(creditCard -> CreditCardInMapper.mapToResource(creditCard).get())
                .collect(Collectors.toList());
    }

}
