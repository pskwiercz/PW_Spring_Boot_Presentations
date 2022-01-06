package com.deepsense.springbootdemo.repository;

import com.deepsense.springbootdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
