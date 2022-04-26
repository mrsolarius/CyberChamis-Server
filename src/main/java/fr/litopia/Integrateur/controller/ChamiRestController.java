package fr.litopia.Integrateur.controller;

import fr.litopia.Integrateur.model.Chami;
import fr.litopia.Integrateur.model.Chami;
import fr.litopia.Integrateur.repository.ChamiRepository;
import fr.litopia.Integrateur.repository.ChamiRepository;
import fr.litopia.Integrateur.services.ChamiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
@RequestMapping("/api/chamis")
public class ChamiRestController {
    @Autowired
    private ChamiService chamiService;

    @PostMapping(value = "/") // avant la création
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Chami createChami(@RequestBody Chami chami) { //equivalent objet
        chamiService.save(chami);
        return chami;
    }

    @GetMapping("/")
    public Collection<Chami> getChamis(){
        return chamiService.findAll();
    }

    @GetMapping("/{id}")
    public Chami getById(@PathVariable("id") String id) {
        Chami chami = chamiService.findById(id);
        if(chami == null){
            throw new NotFoundException("Chami not found");
        }
        return chami;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Chami updateChami(@PathVariable("id") final String id, @RequestBody final Chami chami){
        Chami chamiToUpdate = chamiService.findById(id);
        if(chamiToUpdate == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        chamiToUpdate.setAge(chami.getAge());
        chamiService.save(chamiToUpdate);
        return chamiToUpdate;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteChami(@PathVariable("id") final String id){
        Chami chamiToDelete = chamiService.findById(id);
        if(chamiToDelete == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        chamiService.delete(chamiToDelete);
    }

}
