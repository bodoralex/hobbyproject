package com.codecool.finastra.exception;

public class WrongUserNameOrPasswordException extends Exception{
    public WrongUserNameOrPasswordException() {
        super("The username or password is invalid");
    }
}
