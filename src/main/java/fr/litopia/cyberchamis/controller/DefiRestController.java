package fr.litopia.cyberchamis.controller;
import fr.litopia.cyberchamis.model.dto.ChamisCount;
import fr.litopia.cyberchamis.model.dto.CommentaireDTO;
import fr.litopia.cyberchamis.model.dto.DefiDTO;
import fr.litopia.cyberchamis.model.dto.TagCount;
import fr.litopia.cyberchamis.model.entity.*;
import fr.litopia.cyberchamis.repository.CommentaireRepository;
import fr.litopia.cyberchamis.repository.DefiRepository;
import fr.litopia.cyberchamis.repository.TagRepository;
import fr.litopia.cyberchamis.services.DefiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private TagRepository tagRepository;

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

    @GetMapping("/defi/{id}")
    public Set<DefiDTO> getByChami(@PathVariable("id") Long id) {
        var defis = defiRepository.findDefisByChami(id);
        if (defis.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        Set<DefiDTO> defisDto = new HashSet<>();
        for(Defi d : defis.get()){
            var defi = d.toDTO();
            defisDto.add(defi);
        }
        return defisDto;
    }

    @GetMapping("/tag/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<DefiDTO> getDefisBy(@PathVariable("id") final String tag){
        return this.defiService.findByTag(tag).stream().map(Defi::toDTO).collect(Collectors.toList());
    }


    @GetMapping("tags/count")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TagCount> getTagCount(){
        return tagRepository.countTags().stream().map(TagCount::toTagCount).toList();
    }

    @GetMapping("/defi/nbChamis")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ChamisCount> getDefiNbChamis() {
        StatutVisite statutVisite = StatutVisite.FINISHED;
        return defiRepository.countNbVisite(statutVisite).stream().map(ChamisCount::toChamisCount).toList();
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

    @GetMapping("/defi/{idgoogle}/{statut}")
    public Set<DefiDTO> getDefisByUserStatut(@PathVariable("idgoogle") String idgoogle, @PathVariable("statut")StatutVisite statut) {
        var defis = defiRepository.getDefisByUserStatut(idgoogle, statut);
        if (defis.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        Set<DefiDTO> defisDto = new HashSet<>();
        for(Defi d : defis.get()){
            var defi = d.toDTO();
            defisDto.add(defi);
        }
        return defisDto;
    }


}
