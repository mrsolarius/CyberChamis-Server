package fr.litopia.Integrateur.controller;

import fr.litopia.Integrateur.model.Chami;
import fr.litopia.Integrateur.model.Defi;
import fr.litopia.Integrateur.repository.ChamiRepository;
import fr.litopia.Integrateur.repository.DefiRepository;
import fr.litopia.Integrateur.services.DefiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
@RequestMapping("/api/defis")
public class DefiRestController {
    @Autowired
    private DefiService defiService;

    @PostMapping(value = "/") // avant la création
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Defi createDefi(@RequestBody Defi defi) { //equivalent objet
        defiService.save(defi);
        return defi;
    }

    @GetMapping("/")
    public Collection<Defi> getDefis(){
        return defiService.findAll();
    }

    @GetMapping("/{id}")
    public Defi getById(@PathVariable("id") String id) {
        Defi defi = defiService.findById(id);
        if(defi == null){
            throw new NotFoundException("Defi not found");
        }
        return defi;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Defi updateDefi(@PathVariable("id") final String id, @RequestBody final Defi defi){
        Defi defiToUpdate = defiService.findById(id);
        if(defiToUpdate == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        defiToUpdate.setTitre(defi.getTitre());
        defiToUpdate.setDescription(defi.getDescription());
        defiService.save(defiToUpdate);
        return defiToUpdate;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteChami(@PathVariable("id") final String id){
        Defi defiToDelete = defiService.findById(id);
        if(defiToDelete == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        defiService.delete(defiToDelete);
    }

}
