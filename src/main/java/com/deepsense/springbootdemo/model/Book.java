package com.deepsense.springbootdemo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class Book {

    @Id
    private long id;

    private String author;

    private String title;

    private int pages;

    private boolean discount;

}
