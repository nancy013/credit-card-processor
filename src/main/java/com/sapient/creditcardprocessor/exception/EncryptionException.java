package com.sapient.creditcardprocessor.exception;

public class EncryptionException extends RuntimeException {
  public EncryptionException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
