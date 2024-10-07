package com.microservice.cqrs.BookService.command.event;

import com.microservice.cqrs.BookService.command.data.entity.BookE;
import com.microservice.cqrs.BookService.command.data.repository.BookRepo;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookEventsHandler {

    @Autowired
    private BookRepo bookRepo;

    @EventHandler
    public void on(BookCreateEvent event) {
        BookE book = new BookE();
        BeanUtils.copyProperties(event, book);
        bookRepo.save(book);
    }

    @EventHandler
    public void on(BookUpdateEvent event) {
        Optional<BookE> oldBook = bookRepo.findById(event.getId());
        if (oldBook.isPresent()) {
            BookE book = oldBook.get(); //get o trong option ra
            book.setAuthor(event.getAuthor());
            book.setName(event.getName());
            book.setIsReady(event.getIsReady());
            bookRepo.saveAndFlush(book);
        }
    }

    @EventHandler
    public void on(BookDeleteEvent event) {
        Optional<BookE> oldBook = bookRepo.findById(event.getBookId());
        if (oldBook.isPresent()) {
            bookRepo.delete(oldBook.get());
        }
    }
}
