package com.microservice.cqrs.BookService.command.data.mgt;

import lombok.Data;

@Data
public class ResponseObject<T> {

    private String status;
    private String message;
    private T data;

}
