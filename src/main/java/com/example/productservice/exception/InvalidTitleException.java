package com.example.productservice.exception;

public class InvalidTitleException extends RuntimeException{
    public InvalidTitleException(){
        super("Invalid Title Input");
    }
}
