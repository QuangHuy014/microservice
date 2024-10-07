package com.microservice.cqrs.BookService.command.event;

import lombok.Data;

@Data
public class BookUpdateEvent {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
