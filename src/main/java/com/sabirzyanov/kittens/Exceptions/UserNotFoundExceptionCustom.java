package com.sabirzyanov.kittens.Exceptions;

public class UserNotFoundExceptionCustom extends RuntimeException {
    public UserNotFoundExceptionCustom(String msg) {
        super(msg);
    }

    public UserNotFoundExceptionCustom(String msg, Throwable t) {
        super(msg, t);
    }
}
