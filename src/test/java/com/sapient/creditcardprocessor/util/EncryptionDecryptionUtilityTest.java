package com.sapient.creditcardprocessor.util;

import com.sapient.creditcardprocessor.service.CreditCardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EncryptionDecryptionUtility.class)
class EncryptionDecryptionUtilityTest {
    @Test
    @DisplayName("should check if encryption and decryption follows the same logic")
    void givenSensitiveDataWhenEncryptCalledThenEncryptionSuccess() {
        String data = "1358954993914435";
        String encryptedString = EncryptionDecryptionUtility.encrypt(data);
        String decryptedString = EncryptionDecryptionUtility.decrypt(encryptedString);
        assertThat(encryptedString).isNotEqualTo(data);
        assertThat(decryptedString).isEqualTo(data);
    }
}
