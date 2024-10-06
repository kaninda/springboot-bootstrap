package com.aka.simplebootstrap.repository;

import com.aka.simplebootstrap.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle (String title);
}
