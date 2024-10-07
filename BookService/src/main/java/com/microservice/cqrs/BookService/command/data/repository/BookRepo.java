package com.microservice.cqrs.BookService.command.data.repository;

import com.microservice.cqrs.BookService.command.data.entity.BookE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookE,String> {
}
