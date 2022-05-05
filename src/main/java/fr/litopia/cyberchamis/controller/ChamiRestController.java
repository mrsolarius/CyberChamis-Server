package fr.litopia.cyberchamis.controller;

import fr.litopia.cyberchamis.model.dto.ChamiDTO;
import fr.litopia.cyberchamis.model.entity.Chami;
import fr.litopia.cyberchamis.services.ChamiService;
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
        try {
            Chami chami = chamiService.findById(id);
            return chami.toDTO();
        } catch (Exception e) {
            throw new NotFoundException("Chami not found");
        }
    }

    @GetMapping("/google/{idGoogle}")
    public ChamiDTO getByIdGoogle(@PathVariable("idGoogle") String idGoogle) {
        Chami chami = chamiService.findByIdGoogle(idGoogle);
        if (chami == null) {
            throw new NotFoundException("Chami not found");
        }
        return chami.toDTO();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ChamiDTO updateChami(@PathVariable("id") final long id, @RequestBody final ChamiDTO chami) {
        try {
            Chami chamiToUpdate = chamiService.findById(id);
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
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteChami(@PathVariable("id") final long id) {
        try {
            Chami chamiToDelete = chamiService.findById(id);
            chamiService.delete(chamiToDelete);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @PutMapping("/google/{idGoogle}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ChamiDTO updateChami(@PathVariable("idGoogle") final String idGoogle, @RequestBody final ChamiDTO chami) {
        Chami chamiToUpdate = chamiService.findByIdGoogle(idGoogle);
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
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "entity not valid"
            );
        }
        return chamiToUpdate.toDTO();
    }

    @DeleteMapping("/google/{idGoogle}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteChami(@PathVariable("idGoogle") final String idGoogle) {
        Chami chamiToDelete = chamiService.findByIdGoogle(idGoogle);
        if (chamiToDelete == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        chamiService.delete(chamiToDelete);
    }

}
