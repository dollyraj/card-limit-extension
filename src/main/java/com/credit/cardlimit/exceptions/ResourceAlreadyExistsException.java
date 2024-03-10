package com.credit.cardlimit.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String msg)
    {
        super(msg);
    }
}
