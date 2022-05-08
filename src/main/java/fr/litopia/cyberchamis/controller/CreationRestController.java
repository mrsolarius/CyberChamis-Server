package fr.litopia.cyberchamis.controller;
import fr.litopia.cyberchamis.model.dto.*;
import fr.litopia.cyberchamis.model.dto.creationModif.DefiCreateDTO;
import fr.litopia.cyberchamis.model.dto.creationModif.EtapeCreateDTO;
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
import java.util.Comparator;
import java.util.List;
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

    @PostMapping(value = "/") // avant la création
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public DefiCreateDTO createDefi(@RequestBody DefiCreateDTO defi) { //equivalent objet
        var d = defiRepository.save(toSaveDefiEntity(defi));
        return d.toCreateDefiDTO();
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
        this.tagRepository.save(t);
        this.defiRepository.save(d);
        d.addTag(t);

    }

    //Gneerer et sauvgarder les dto en utilisant la bdd
    //Sera utiliser pour l'edition et creation donc l'id du defis et à verifier

    @Transactional
    public Defi toSaveDefiEntity(DefiCreateDTO defiDTO) {
        var chami = chamiRepository.findById(defiDTO.auteurId);
        if (chami.isEmpty()) {
            throw new NotFoundException("chami not found");
        }
        var defi = defiRepository.findById(defiDTO.id);
        Defi d = null;
        if (defi.isEmpty()) {
            //Pas de defis : Crée un tout nouveau defis à partir du dto
            d = new Defi();

        } else {
            //Quand un défiexiste il faut le modifier en utilisant defi.get().set les truc qui change voilà
            d = defi.get();
            removeAllEtape(d);
        }
        if (defiDTO.titre != null) {
            d.setTitre(defiDTO.titre);
        }
        if (defiDTO.miniDescription != null) {
            d.setMiniDescription(defiDTO.miniDescription);
        }
        if (defiDTO.description != null) {
            d.setDescription(defiDTO.description);
            ;
        }
        if (defiDTO.duree != null) {
            d.setDuree(defiDTO.duree);
        }
        if (defiDTO.auteurId != null) {
            d.setAuteur(chami.get());
        }
        if (defiDTO.arret != null) {
            d.setArret(defiDTO.arret.toEntity());
        }
        if (!defiDTO.etapes.isEmpty()) {
            var listeEtape = List.copyOf(defiDTO.etapes);
            for (EtapeCreateDTO etapeCreateDTO : listeEtape.stream().sorted(Comparator.comparing(EtapeCreateDTO::getNumero)).toList()) {
                var e = toSaveEtapeEntity(etapeCreateDTO);
                d.addEtape(e);
            }
        }
        if (!defiDTO.tags.isEmpty()) {
            for (String tagDTO : defiDTO.tags) {
                var t = toSaveTagEntity(tagDTO);
                d.addTag(t);
            }
        }
        return defiRepository.save(d);
    }

    @Transactional
    public Tag toSaveTagEntity(String tagDTO) {
        var tag = tagRepository.findById(tagDTO);
        if(tag.isEmpty()){
            Tag tagE = new Tag();
            tagE.tag = tagDTO;
            return tagRepository.save(tagE);
        }else{
            return tag.get();
        }
    }

    @Transactional
    public Etape toSaveEtapeEntity(EtapeCreateDTO etape ){
        Etape entity=null;
        if (etape.type == TypeEtapeDTO.TacheDTO) {
            var t = tacheRepository.findById(etape.idEtape);
            if (t.isEmpty()) {
                entity = new Tache();
            } else {
                entity = t.get();
            }
            entity.setTitre(etape.titreEtape);
            entity.setDescription(etape.descriptionEtape);
            entity.setNumero(etape.numero);
            ((Tache)entity).setQuestion(etape.question);
            ((Tache)entity).setPoint(etape.point);
            ((Tache)entity).setSecret(etape.secret);
            ((Tache)entity).setIndices(etape.indices.
                    stream().map(IndiceDTO::toEntity).collect(Collectors.toSet()));
            tacheRepository.save((Tache) entity);
        }else if (etape.type == TypeEtapeDTO.IndicationDTO){
            var i = indicationRepository.findById(etape.idEtape);
            if (i.isEmpty()) {
                entity= new Indication();
            } else {
                entity= i.get();
            }
            entity.setTitre(etape.titreEtape);
            entity.setDescription(etape.descriptionEtape);
            entity.setNumero(etape.numero);
            ((Indication)entity).setText(etape.text);
            ((Indication)entity).setImage(etape.image);
            ((Indication)entity).setVideo(etape.video);
            indicationRepository.save((Indication)entity);
        }
        return entity;
    }

    @Transactional
    void removeAllEtape(Defi defi){
        for (Etape e: defi.getEtapes()) {
            if (e instanceof Tache){
                indiceRepository.deleteAll(((Tache) e).getIndices());
                tacheRepository.delete((Tache) e);
            }else if (e instanceof Indication){
                indicationRepository.delete((Indication) e);
            }
        }
    }
}