package com.sapient.creditcardprocessor.adapter.in.web.resource;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@Data
public class ErrorResponse {

    @Parameter(
            description = "The list of errors.",
            required = true)
    private List<Error> errors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Error {
        @Parameter(
                description = "The field for which the validation constraint is applicable",
                required = true)
        private String field;

        @Parameter(
                description = "The Error description for the field",
                required = true)
        private String errorMessage;
    }
}
