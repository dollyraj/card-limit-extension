package com.credit.cardlimit.exceptions;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String msg)
    {
        super(msg);
    }
}
