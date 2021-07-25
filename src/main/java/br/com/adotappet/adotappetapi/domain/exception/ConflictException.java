package br.com.adotappet.adotappetapi.domain.exception;

public class ConflictException extends BusinessException {
    public ConflictException(String message) {
        super(message);
    }
}
