package com.akinms.apirestful.exception;

public class BusinessException extends Exception{
    public BusinessException(String mensaje){
        super(mensaje);
    }
}
