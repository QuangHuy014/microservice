package com.microservice.cqrs.BookService.command.command;


import com.microservice.cqrs.BookService.command.event.BookCreateEvent;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@Builder
public class CreateBookCommand {

    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

}
