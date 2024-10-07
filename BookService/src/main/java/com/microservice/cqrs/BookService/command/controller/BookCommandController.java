package com.microservice.cqrs.BookService.command.controller;

import com.microservice.cqrs.BookService.command.command.CreateBookCommand;
import com.microservice.cqrs.BookService.command.command.DeleteBookCommand;
import com.microservice.cqrs.BookService.command.command.UpdateBookCommand;
import com.microservice.cqrs.BookService.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addBook(@RequestBody BookRequestModel model) {
        CreateBookCommand command = CreateBookCommand.builder()
                .id(UUID.randomUUID().toString())
                .name(model.getName())
                .author(model.getAuthor())
                .isReady(true)
                .build();
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{bookId}")
    public String updateBook(@RequestBody BookRequestModel model, @PathVariable String bookId) {
        UpdateBookCommand command = UpdateBookCommand.builder()
                .id(bookId)
                .name(model.getName())
                .author(model.getAuthor())
                .isReady(model.getIsReady())
                .build();
        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        DeleteBookCommand command = DeleteBookCommand.builder()
                .bookId(bookId)
                .build();
        return commandGateway.sendAndWait(command);
    }

}
