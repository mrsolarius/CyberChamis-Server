package fr.litopia.Integrateur.controller;

import fr.litopia.Integrateur.model.Chami;
import fr.litopia.Integrateur.repository.ChamiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.Collection;

@RestController //indique qu'il faut injecter cette classe en tant que contrôleur REST. Dans le Framework Spring, un contrôleur permet de répondre à des requêtes HTTP avec des données quelconques (pas nécessairement du HTML).
@RequestMapping(value = "/api/chamis",produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChamiRestController {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ChamiRepository chamiRepository;
    ChamiRestController(ChamiRepository chamiRepository) {
        this.chamiRepository = chamiRepository;
    }

    @PostMapping(value = "/add") // avant la création
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Chami createChami(@RequestBody final Chami chami) { //equivalent objet
        return chamiRepository.save(chami);
    }

    @GetMapping("/")
    public Collection<Chami> getChamis(){
        return chamiRepository.findAll();
    }

    @GetMapping("/{login}")
    public Chami findById(@PathVariable("login") String login) {
        return chamiRepository.findById(login)
                .orElseThrow(() -> new NotFoundException("Chami not found"));
    }

    @PutMapping("/{login}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Chami putChami(@PathVariable("login") final String login, @RequestBody final Chami chami){
        Chami oldChami = chamiRepository.getReferenceById(login);
        if(oldChami == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        oldChami = chami;
        chamiRepository.save(oldChami);
        return oldChami;
    }
}