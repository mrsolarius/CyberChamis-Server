package fr.litopia.Integrateur.controller;

import fr.litopia.Integrateur.model.Chami;
import fr.litopia.Integrateur.model.Defi;
import fr.litopia.Integrateur.repository.ChamiRepository;
import fr.litopia.Integrateur.repository.DefiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@RestController
@RequestMapping("/api/defis")
public class DefiRestController {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private DefiRepository defiRepository;

    DefiRestController(DefiRepository defiRepository) {
        this.defiRepository = defiRepository;
    }

    @PostMapping(value = "/") // avant la création
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Defi createDefi(@RequestBody Defi defi) { //equivalent objet
        return defiRepository.save(defi);
    }

    @GetMapping("/")
    public Collection<Defi> getDefis(){
        return defiRepository.findAll();
    }

    @GetMapping("/{id}")
    public Defi findById(@PathVariable("id") String id) {
        return defiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Defi not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Defi putChami(@PathVariable("id") final String id, @RequestBody final Defi defi){
        Defi oldDefi = defiRepository.getReferenceById(id);
        if(oldDefi == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        oldDefi = defi;
        defiRepository.save(oldDefi);
        return oldDefi;
    }
}
