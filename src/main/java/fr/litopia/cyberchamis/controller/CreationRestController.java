package fr.litopia.cyberchamis.controller;
import fr.litopia.cyberchamis.model.dto.*;
import fr.litopia.cyberchamis.model.dto.creationModif.DefiCreateDTO;
import fr.litopia.cyberchamis.model.dto.creationModif.EtapeCreateDTO;
import fr.litopia.cyberchamis.model.entity.*;
import fr.litopia.cyberchamis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
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
    private TagRepository tagRepository;
    @Autowired
    private IndiceRepository indiceRepository;
    @Autowired
    private IndicationRepository indicationRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private ArretRepository arretRepository;

    @PostMapping(value = "/") // avant la création
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public DefiCreateDTO createDefi(@RequestBody DefiCreateDTO defi) { //equivalent objet
        var d = defiRepository.save(toSaveDefiEntity(defi));
        return d.toCreateDefiDTO();
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public DefiCreateDTO createOrUpdateDefi(@RequestBody DefiCreateDTO defi) {
        try {
            return this.toSaveDefiEntity(defi).toCreateDefiDTO();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public DefiCreateDTO getFullDefi(@RequestParam String id){
        var data = this.defiRepository.findById(id);
        if(data.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }else{
            return data.get().toCreateDefiDTO();
        }
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
        var id = "null";
        if (defiDTO.id != null)id=defiDTO.id;
        var defi = defiRepository.findById(id);
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
        }
        if (defiDTO.duree != null) {
            d.setDuree(defiDTO.duree);
        }
        if (defiDTO.auteurId != null) {
            d.setAuteur(chami.get());
        }
        if(defiDTO.img!=null){
            d.setImg(defiDTO.img);
        }
        if (defiDTO.arret != null) {
            d.setArret(toSaveArretEntity(defiDTO.arret));
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
        var tagId = "";
        if(tagDTO!=null)tagId = tagDTO;
        var tag = tagRepository.findById(tagId);
        if(tag.isEmpty()){
            Tag tagE = new Tag(tagDTO);
            return tagRepository.save(tagE);
        }else{
            return tag.get();
        }
    }

    @Transactional
    public Etape toSaveEtapeEntity(EtapeCreateDTO etape ){
        Etape entity=null;
        if (etape.type == TypeEtapeDTO.TacheDTO) {
            var id = -1L;
            if (etape.idEtape != null)id=etape.idEtape;
            var t = tacheRepository.findById(id);
            if (t.isEmpty()) {
                entity = new Tache();
            } else {
                entity = t.get();
            }
            entity.setTitre(etape.titreEtape);
            entity.setDescription(etape.descriptionEtape);
            entity.setNumero(etape.numero);
            entity.setBanner(etape.banner);
            ((Tache)entity).setQuestion(etape.question);
            ((Tache)entity).setPoint(etape.point);
            ((Tache)entity).setSecret(etape.secret);
            var listeIndice = List.copyOf(etape.indices);
            for (IndiceDTO indiceDTO : listeIndice.stream().sorted(Comparator.comparing(IndiceDTO::getNumIndice)).toList()) {
                var i = toSaveIndiceEntity(indiceDTO);
                ((Tache)entity).addIndice(i);
            }
            tacheRepository.save((Tache) entity);
        }else if (etape.type == TypeEtapeDTO.IndicationDTO){
            var id = -1L;
            if (etape.idEtape != null)id=etape.idEtape;
            var i = indicationRepository.findById(id);
            if (i.isEmpty()) {
                entity= new Indication();
            } else {
                entity= i.get();
            }
            entity.setTitre(etape.titreEtape);
            entity.setDescription(etape.descriptionEtape);
            entity.setNumero(etape.numero);
            entity.setBanner(etape.banner);
            ((Indication)entity).setText(etape.text);
            ((Indication)entity).setImage(etape.image);
            ((Indication)entity).setVideo(etape.video);
            indicationRepository.save((Indication)entity);
        }
        return entity;
    }

    @Transactional
    Indice toSaveIndiceEntity(IndiceDTO indiceDTO) {
        Indice entity = null;
        var id = -1L;
        if (indiceDTO.id!=null)id=indiceDTO.id;
        var  i = indiceRepository.findById(id);
        if (i.isEmpty()){
            entity = new Indice();
        }else{
            entity = i.get();
        }
        entity.setNumIndice(indiceDTO.numIndice);
        entity.setIndice(indiceDTO.indice);
        entity.setPointsPerdus(indiceDTO.pointsPerdus);
        return indiceRepository.save(entity);
    }

    @Transactional
    public Arret toSaveArretEntity(ArretDTO arret) {
        Arret entity = null;
        var id = "";
        if (arret.codeArret!=null)id=arret.codeArret;
        var a = arretRepository.findById(id);
        if (a.isEmpty()){
            entity = new Arret();
            entity.setCodeArret(arret.codeArret);
            entity.setNomArret(arret.nomArret);
            entity.setVille(arret.ville);
            entity.setLatitude(arret.latitude);
            entity.setLongitude(arret.longitude);
            return arretRepository.save(entity);
        }else{
            entity = a.get();
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