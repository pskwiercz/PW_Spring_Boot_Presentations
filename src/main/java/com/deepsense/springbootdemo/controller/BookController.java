package com.deepsense.springbootdemo.controller;

import com.deepsense.springbootdemo.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

//@Tag(name = "Book Rest API", description = "Operations on books via Rest API")
public interface BookController {

    ResponseEntity<Book> getBookById(Long bookId);

//    @Operation(description = "View a list of available books")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = Book.class))}),
//            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = {@Content(mediaType = "", schema = @Schema())}),
//            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = {@Content(mediaType = "", schema = @Schema())}),
//            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found", content = {@Content(mediaType = "", schema = @Schema())})
//    })
    List<Book> getAllBooks();

//    @Operation(description = "Add book")
//    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))})})
    Book createBook( @Parameter(description = "Store Book object in database", required = true) Book book);

//    @Operation(description = "Delete book")
//    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully added book",  content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))})})
    Map<String, Boolean> deleteBook(
            @Parameter(description = "Book Id for book which will be deleted ", required = true) @PathVariable(value = "id") Long bookId);

}
