package fr.litopia.cyberchamis.controller;
import fr.litopia.cyberchamis.model.dto.*;
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

}
}