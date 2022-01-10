package com.deepsense.springbootdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
//@Schema(description = "All details about the book.")
@XmlRootElement
public class Book {

    @Id
//    @Schema(description = "Book ID", minLength = 1, required = true)
    private long id;

//    @Schema(description = "Author of the book", example="John Dole")
    private String author;

//    @Schema(description = "Title of the book")
    private String title;

//    @Schema(description = "Number of pages")
    private int pages;

//    @Schema(description = "Discount availability")
    private boolean discount;

}
