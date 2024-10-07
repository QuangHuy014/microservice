package com.microservice.cqrs.BookService.command.aggregate;


import com.microservice.cqrs.BookService.command.command.CreateBookCommand;
import com.microservice.cqrs.BookService.command.command.DeleteBookCommand;
import com.microservice.cqrs.BookService.command.command.UpdateBookCommand;
import com.microservice.cqrs.BookService.command.event.BookCreateEvent;
import com.microservice.cqrs.BookService.command.event.BookDeleteEvent;
import com.microservice.cqrs.BookService.command.event.BookUpdateEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class BookAggregate {

    @AggregateIdentifier //cai nay no se lien net den createbookcommand giup cho khi ma minh phat ra command thi no se lien ket toi th book de quan ly cac trang thai thay doi
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

    @CommandHandler
    public BookAggregate(CreateBookCommand command){
        BookCreateEvent BCE = new BookCreateEvent();
        BeanUtils.copyProperties(command,BCE);

        AggregateLifecycle.apply(BCE);//public event
    }

    @CommandHandler
    public void handler(UpdateBookCommand command){
        BookUpdateEvent bookUpdateEvent = new BookUpdateEvent();
        BeanUtils.copyProperties(command,bookUpdateEvent);
         AggregateLifecycle.apply(bookUpdateEvent);//public event
    }

    @CommandHandler
    public void handler(DeleteBookCommand command){
        BookDeleteEvent bookDeleteEvent = new BookDeleteEvent();
        BeanUtils.copyProperties(command,bookDeleteEvent);
        AggregateLifecycle.apply(bookDeleteEvent);//public event
    }


    @EventSourcingHandler//su dung de xu ly va cap nhat trang thai
    public void on(BookCreateEvent bookCreateEvent){
        this.id = bookCreateEvent.getId();
        this.name = bookCreateEvent.getName();
        this.author = bookCreateEvent.getAuthor();
        this.isReady = bookCreateEvent.getIsReady();
    }

    @EventSourcingHandler//su dung de xu ly va cap nhat trang thai
    public void on(BookUpdateEvent bookUpdateEvent){
        this.id = bookUpdateEvent.getId();
        this.name = bookUpdateEvent.getName();
        this.author = bookUpdateEvent.getAuthor();
        this.isReady = bookUpdateEvent.getIsReady();
    }

    @EventSourcingHandler//su dung de xu ly va cap nhat trang thai
    public void on(DeleteBookCommand deleteBookCommand){
        this.id = deleteBookCommand.getBookId();
    }
}
