package com.sapient.creditcardprocessor.adapter.in.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.creditcardprocessor.adapter.in.web.mapper.CreditCardInMapper;
import com.sapient.creditcardprocessor.adapter.in.web.resource.CreditCardRequest;
import com.sapient.creditcardprocessor.domain.model.CreditCard;
import com.sapient.creditcardprocessor.port.in.CreditCardInPort;
import com.sapient.creditcardprocessor.util.EncryptionDecryptionUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class CreditCardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardInPort creditCardInPort;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("should return Bad Request when name is empty")
    void shouldReturnBadRequestWhenNameIsEmptyTest() throws Exception {
        CreditCardRequest creditCardRequest = createCreditCardRequest(request -> request.setName(""));
        CreditCard creditCard = mockCreditCard();
        try (MockedStatic<CreditCardInMapper> utilities = Mockito.mockStatic(CreditCardInMapper.class)) {
            utilities.when(() -> CreditCardInMapper.mapToDomain(creditCardRequest))
                    .thenReturn(Optional.of(creditCard));
            mockMvc.perform(post("/api/v1/creditcards")
                    .content(objectMapper.writeValueAsString(creditCardRequest))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errors[0].field",
                            equalTo("name")));
        }
    }

    @Test
    @DisplayName("should return Bad Request when card number is empty")
    void shouldReturnBadRequestWhenCardNumerIsEmptyTest() throws Exception {
        mockMvc.perform(post("/api/v1/creditcards")
                .content(objectMapper.writeValueAsString(createCreditCardRequest(request -> request.setCardNumber(""))))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].field",
                        equalTo("cardNumber")));
    }
    @Test
    @DisplayName("should return 201 created when data is valid")
    void shouldReturnAcceptedWhenDataIsValid() throws Exception {
        mockMvc.perform(post("/api/v1/creditcards")
                .content(objectMapper.writeValueAsString(createCreditCardRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("should return list of credit cards")
    void shouldReturnCreditCards() throws Exception {
        try (MockedStatic<EncryptionDecryptionUtility> utilities = Mockito.mockStatic(EncryptionDecryptionUtility.class)) {
            utilities.when(() -> EncryptionDecryptionUtility.decrypt("cSZ3ZKTTe85g7xY9M1PefI4zFAoKHBAmZG87zXq/30s="))
                    .thenReturn("1358954993914435");
            List<CreditCard> mockCreditCardList = IntStream.range(0, 2)
                    .mapToObj(index -> mockCreditCard(creditCard -> creditCard.setCardNumber("cSZ3ZKTTe85g7xY9M1PefI4zFAoKHBAmZG87zXq")))
                    .collect(Collectors.toList());
            when(creditCardInPort.findAll(1,3)).thenReturn(Optional.of(mockCreditCardList));
            mockMvc.perform(get("/api/v1/creditcards?page=1&size=3")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$",
                            hasSize(2)));
        }

    }
    /** Create Input Data **/
    private CreditCardRequest createCreditCardRequest(final Consumer<CreditCardRequest> consumer) {
        final CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setName("XYZ");
        creditCardRequest.setCreditLimit(0.0);
        creditCardRequest.setCardNumber("1358954993914435");

        if (nonNull(consumer)) {
            consumer.accept(creditCardRequest);
        }
        return creditCardRequest;
    }
    public  CreditCardRequest createCreditCardRequest() {
        return createCreditCardRequest(null);
    }

    /** Mock Data fro Credit Card **/
    public static CreditCard mockCreditCard(final Consumer<CreditCard> consumer) {
        final CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("1358954993914435");
        creditCard.setName("XYZ");
        creditCard.setCreditLimit(100.00);
        creditCard.setId(UUID.randomUUID());
        creditCard.setCreatedTime(Timestamp.from(Instant.now()));
        creditCard.setUpdatedTime(Timestamp.from(Instant.now()));

        if (nonNull(consumer)) {
            consumer.accept(creditCard);
        }

        return creditCard;
    }

    public static CreditCard mockCreditCard() {
        return mockCreditCard(null);
    }

}
