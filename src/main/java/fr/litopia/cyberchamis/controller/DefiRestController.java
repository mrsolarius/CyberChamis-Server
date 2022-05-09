package fr.litopia.cyberchamis.controller;
import fr.litopia.cyberchamis.model.dto.DefiDTO;
import fr.litopia.cyberchamis.model.entity.Defi;
import fr.litopia.cyberchamis.repository.CommentaireRepository;
import fr.litopia.cyberchamis.repository.DefiRepository;
import fr.litopia.cyberchamis.services.DefiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController //indique qu'il faut injecter cette classe en tant que contrôleur REST. Dans le Framework Spring, un contrôleur permet de répondre à des requêtes HTTP avec des données quelconques (pas nécessairement du HTML).
@RequestMapping(value = "/api/defis",produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DefiRestController {
    @Autowired
    private DefiService defiService;

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private DefiRepository defiRepository;

    @PostMapping(value = "/") // avant la création
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public DefiDTO createDefi(@RequestBody DefiDTO defi) { //equivalent objet
        defiService.save(defi.toEntity());
        return defi;
    }

    @GetMapping("/")
    public Collection<DefiDTO> getDefis(){
        return defiService.findAll().stream().map(Defi::toDTO).toList();
    }

    @GetMapping("/{id}")
    public DefiDTO getById(@PathVariable("id") String id) {
        Defi defi = defiService.findById(id);
        if(defi == null){
            throw new NotFoundException("Defi not found");
        }
        return defi.toDTO();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public DefiDTO updateDefi(@PathVariable("id") final String id, @RequestBody final DefiDTO defi){
        Defi defiToUpdate = defiService.findById(id);
        if(defiToUpdate == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        defiService.save(defi.toEntity());
        return defi;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteDefi(@PathVariable("id") final String id){
        Defi defiToDelete = defiService.findById(id);
        if(defiToDelete == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        defiService.delete(defiToDelete);
    }

    @DeleteMapping("/commentaire/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteCommentaireFromDefi(@PathVariable("id") final Long id){
        var comToDelete = commentaireRepository.findById(id);
        if(comToDelete.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        var defi = defiRepository.findDefiByCom(id);
        if(defi.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        defi.get().supprimerUnCommentaire(comToDelete.get());
        defiRepository.save(defi.get());
        commentaireRepository.delete(comToDelete.get());
    }

}
