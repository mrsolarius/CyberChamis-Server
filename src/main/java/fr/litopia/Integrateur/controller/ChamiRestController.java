package fr.litopia.Integrateur.controller;

import fr.litopia.Integrateur.model.dto.ChamiDTO;
import fr.litopia.Integrateur.model.entity.Chami;
import fr.litopia.Integrateur.services.ChamiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
//indique qu'il faut injecter cette classe en tant que contrôleur REST. Dans le Framework Spring, un contrôleur permet de répondre à des requêtes HTTP avec des données quelconques (pas nécessairement du HTML).
@RequestMapping(value = "/api/chamis", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChamiRestController {
    @Autowired
    private ChamiService chamiService;

    @PostMapping(value = "/") // avant la création
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ChamiDTO createChami(@RequestBody ChamiDTO chami) { //equivalent objet
        Chami c = chami.toEntity();
        chamiService.save(chami.toEntity());
        return chami;
    }

    @GetMapping("/")
    public Collection<ChamiDTO> getChamis() {
        return chamiService.findAll().stream().map(Chami::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ChamiDTO getById(@PathVariable("id") long id) {
        Chami chami = chamiService.findById(id);
        if (chami == null) {
            throw new NotFoundException("Chami not found");
        }
        return chami.toDTO();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ChamiDTO updateChami(@PathVariable("id") final long id, @RequestBody final ChamiDTO chami) {
        Chami chamiToUpdate = chamiService.findById(id);
        if (chamiToUpdate == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        try {
            chamiToUpdate.setUsername(chami.username);
            chamiToUpdate.setAge(chami.age);
            chamiToUpdate.setBio(chami.bio);
            chamiService.save(chamiToUpdate);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "entity not valid"
            );
        }
        return chamiToUpdate.toDTO();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteChami(@PathVariable("id") final long id) {
        Chami chamiToDelete = chamiService.findById(id);
        if (chamiToDelete == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        chamiService.delete(chamiToDelete);
    }

}
