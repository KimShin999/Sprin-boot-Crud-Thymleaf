package com.example.ks.repository;

import com.example.ks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IBookRepository extends JpaRepository<Book, Long> {
}
