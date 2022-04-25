package fr.litopia.Integrateur.controller;

import fr.litopia.Integrateur.model.Book;
import fr.litopia.Integrateur.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Collection;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/{id}")
    public Book findById(@PathVariable long id) {
        return (Book) service.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @GetMapping("/")
    public Collection<Book> findBooks() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@PathVariable("id") final String id, @RequestBody final Book book) {
        Book bookToUpdate = findById(Long.parseLong(id));
        bookToUpdate.setName(book.getName());
        service.save(bookToUpdate);
        return bookToUpdate;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody final Book book) {
        service.save(book);
        return book;
    }
}