package fr.litopia.cyberchamis.controller;
import fr.litopia.cyberchamis.model.dto.EtapeDTO;
import fr.litopia.cyberchamis.model.dto.IndiceDTO;
import fr.litopia.cyberchamis.model.dto.TagDTO;
import fr.litopia.cyberchamis.model.dto.TypeEtapeDTO;
import fr.litopia.cyberchamis.model.dto.creationModif.DefiCreateDTO;
import fr.litopia.cyberchamis.model.dto.creationModif.EtapeCreateDTO;
import fr.litopia.cyberchamis.model.dto.creationModif.IndicationCreateDTO;
import fr.litopia.cyberchamis.model.dto.creationModif.TacheCreateDTO;
import fr.litopia.cyberchamis.model.entity.*;
import fr.litopia.cyberchamis.repository.*;
import fr.litopia.cyberchamis.services.DefiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/api/defibuilder", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CreationRestController {
    @Autowired
    private DefiRepository defiRepository;
    @Autowired
    private ChamiRepository chamiRepository;
    @Autowired
    private EtapeRepository etapeRepository;
    @Autowired
    private DefiService defiService;
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private IndiceRepository indiceRepository;
    @Autowired
    private IndicationRepository indicationRepository;

    @Autowired
    private TacheRepository tacheRepository;

    /**
     * Creation defi DefiCreateDTO post
     * AjouterDesEtape EtapeCreateDTO & idDefi post
     * AjouterDesIndice IndiceDTO & idEtape post
     */
    @PostMapping(value = "/") // avant la création
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public DefiCreateDTO createDefi(@RequestBody DefiCreateDTO defi) { //equivalent objet
        var d = defiRepository.save(toSaveDefiEntity(defi));
        return d.toDefiCreateDTO();
    }
    //Gneerer et sauvgarder les dto en utilisant la bdd
    //Sera utiliser pour l'edition et creation donc l'id du defis et à verifier
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Defi toSaveDefiEntity(DefiCreateDTO defi){
        var chami = chamiRepository.findById(defi.auteurId);
        if(chami.isEmpty()) {
            throw new NotFoundException("chami not found");
        }
        Defi d = new Defi();
        if (defi.titre!=null){
            d.titre=defi.titre;
        }
        else if (defi.description!=null){
            d.description=defi.description;
        }
        else if (defi.version!=null){
            d.version= defi.version;
        }
        else if (defi.duree!=null){
            d.duree= defi.duree;
        }
        else if (defi.auteurId!=null){
            d.auteur=chami.get();
        }
        else if (defi.arret!=null){
            d.arret=defi.arret.toEntity();
        }
        else if (! defi.etapes.isEmpty()){
            for ( EtapeCreateDTO e : defi.etapes)
            {
        /*    toSaveEtapeEntity(e);
            saveIndice((Tache) e.);
            saveEtape(d,);
            }*/
        }
        else if ( ! defi.tags.isEmpty()){
            for ( String t : defi.tags)
            {
                toSaveEtapeEntity(e);
            }

        }
        Note n = new Note(note.valeur,chami.get());
        defi.get().ajouterOuModifierNote(n);
        this.noteRepository.save(n);
        this.defiRepository.save(defi.get());
        return d;
    }
    @Transactional
    public Etape toSaveEtapeEntity(EtapeCreateDTO etape ){
        Etape entity=null;
        if (etape.typeEtapeDTO == TypeEtapeDTO.TacheDTO) {
            var t = tacheRepository.findById(etape.idEtape);
            if (t.isEmpty()) {
                throw new NotFoundException("Etape:tache  not found");
            } else {
                t.get().setQuestion(((TacheCreateDTO)etape).question);
                t.get().setPoint(((TacheCreateDTO)etape).point);
                t.get().setSecret(((TacheCreateDTO)etape).secret);
                t.get().setIndices(((TacheCreateDTO)etape).indices.
                        stream().map(IndiceDTO::toEntity).collect(Collectors.toSet());}
        }else if (etape.typeEtapeDTO == TypeEtapeDTO.IndicationDTO){
            var i = indicationRepository.findById(etape.idEtape);
            if (i.isEmpty()) {
                throw new NotFoundException("Etape:Indication  not found");
            } else {
                i.get().setText(((IndicationCreateDTO)etape).toEntity().text);

            }


        }

    }
    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public DefiCreateDTO createOrUpdateDefi(@RequestBody DefiCreateDTO defi) {
        try {
            this.toSaveDefiEntity(defi);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        return defi;
    }
    private void saveEtape(Etape etape, Defi d){
        this.etapeRepository.save(etape);
        d.addEtape(etape);
        this.defiRepository.save(d);
    }

    private void saveIndice(Tache t, Indice i){
        this.etapeRepository.save(t);
        t.addIndice(i);
        this.indiceRepository.save(i);
    }

    private void saveTags(Defi d, Tag t){
        this.defiRepository.save(d);
        d.addTag(t);
        this.tagRepository.save(t);
    }

}
