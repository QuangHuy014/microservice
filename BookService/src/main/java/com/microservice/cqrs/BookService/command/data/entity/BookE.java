package com.microservice.cqrs.BookService.command.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class BookE {
    @Id
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

}
