package com.akinms.apirestful.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String mensaje){
        super(mensaje);
    }
}
