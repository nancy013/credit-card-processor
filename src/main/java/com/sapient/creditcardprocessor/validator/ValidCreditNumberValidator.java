package com.sapient.creditcardprocessor.validator;

import com.sapient.creditcardprocessor.service.LuhnValidatorService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ValidCreditNumberValidator implements ConstraintValidator<ValidCreditNumber, String> {

  private LuhnValidatorService luhnValidatorService;

  public ValidCreditNumberValidator(final LuhnValidatorService luhn10ValidatorService) {
    this.luhnValidatorService = luhn10ValidatorService;
  }

  public boolean isValid(String creditCardNumber, ConstraintValidatorContext context) {

    if (StringUtils.isBlank(creditCardNumber)
      || creditCardNumber.chars().anyMatch(ch -> !Character.isDigit(ch))
      || creditCardNumber.length() > 19) {

      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate("CreditCard Number should be max 19 digits , no characters and must be valid.")
        .addConstraintViolation();
      return false;
    }

    return luhnValidatorService.isValidCreditCardNumber(creditCardNumber);
  }
}
