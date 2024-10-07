package com.microservice.cqrs.BookService.command.event;

import lombok.Data;

@Data
public class BookDeleteEvent {

    private String bookId;

}
