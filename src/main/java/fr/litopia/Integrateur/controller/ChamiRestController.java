package fr.litopia.Integrateur.controller;

import fr.litopia.Integrateur.model.Chami;
import fr.litopia.Integrateur.repository.ChamiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Collection;

@RestController //indique qu'il faut injecter cette classe en tant que contrôleur REST. Dans le Framework Spring, un contrôleur permet de répondre à des requêtes HTTP avec des données quelconques (pas nécessairement du HTML).
@RequestMapping("/api/chamis")
@CrossOrigin
public class ChamiRestController {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ChamiRepository chamiRepository;
    ChamiRestController(ChamiRepository chamiRepository) {
        this.chamiRepository = chamiRepository;
    }

    /*Chami create(@PathVariable(value="userId") String id, @4 User u, HttpServletResponse response){

    }*/

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json") // avant la création
    @ResponseStatus(HttpStatus.CREATED)
    public Chami createChami(@RequestBody Chami chami) { //equivalent objet
        return chamiRepository.save(chami);
    }

    @GetMapping("/")
    public Collection<Chami> getChamis(){
        return chamiRepository.findAll();
    }


        /*
    @Autowired //automatiquement generer
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

    @PostMapping("/") // avant la création
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody final Book book) {
        service.save(book);
        return book;
    }*/
}