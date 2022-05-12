package fr.litopia.cyberchamis.controller;

import fr.litopia.cyberchamis.model.dto.DefiDTO;
import fr.litopia.cyberchamis.model.dto.IndiceDTO;
import fr.litopia.cyberchamis.model.dto.VisiteDTO;
import fr.litopia.cyberchamis.model.entity.*;
import fr.litopia.cyberchamis.repository.*;
import fr.litopia.cyberchamis.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/game", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameRestController {
    @Autowired
    private GameService gameService;

    @Autowired
    private DefiRepository defiRepository;

    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired
    private ChamiRepository chamiRepository;

    @Autowired
    private VisiteRepository visiteRepository;

    @Autowired
    private ArretRepository arretRepository;
    @Autowired
    private IndicationRepository indicationRepository;

    @Autowired
    private IndiceRepository indiceRepository;

    @Autowired
    private TacheRepository tacheRepository;

    @PostMapping("/start-game")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public VisiteDTO startGame(@RequestParam String defiId, @RequestParam Long userId) {
        if (defiRepository.findById(defiId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Defi not found");
        }
        if (userRepository.findById(userId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        Defi defi = defiRepository.findById(defiId).get();
        Utilisateur user = userRepository.findById(userId).get();
        try {
            return gameService.commencerVisite(defi, user).toDTO();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You need to finish this defi before starting a new one");
        }
    }

    @GetMapping("/continue-visite")
    @ResponseStatus(HttpStatus.OK)
    public VisiteDTO visiteCourante(@RequestParam String defiId, @RequestParam long userId) {
        if (defiRepository.findById(defiId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Defi not found");
        }
        if (userRepository.findById(userId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        Defi defi = defiRepository.findById(defiId).get();
        Utilisateur user = userRepository.findById(userId).get();
        try {
            return gameService.reprendreVisite(defi, user).toDTO();
        } catch (RuntimeException e) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You need to start a new game");
        }
    }


    @PostMapping("/next-etape")
    @ResponseStatus(HttpStatus.OK)
    public VisiteDTO etapeSuivante(@RequestParam Long visiteId) {
        Visite v = checkAndGetVisite(visiteId);
        try {
            return gameService.etapeSuivante(v).toDTO();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.GONE, "No more etape");
        }
    }

    @PostMapping("/previsous-etape")
    @ResponseStatus(HttpStatus.OK)
    public VisiteDTO etapePrecedente(@RequestParam Long visiteId) {
        Visite v = checkAndGetVisite(visiteId);
        try {
            return gameService.etapePrecedente(v).toDTO();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.GONE, "No more etape");
        }
    }

    @PostMapping("/check-response")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkResponse(@RequestParam Long visiteId, @RequestParam String response) {
        Visite v = checkAndGetVisite(visiteId);
        try {
            return gameService.checkResponse(response,v);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad response");
        }
    }

    @GetMapping("/response-indice")
    @ResponseStatus(HttpStatus.OK)
    public List<IndiceDTO> getResponseIndices(@RequestParam Long visiteId) {
        Visite v = checkAndGetVisite(visiteId);
        List<Indice> indiceList = gameService.getIndices(v);
        if(indiceList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No indice found");
        }
        return indiceList.stream().map(Indice::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/reveal-indice")
    @ResponseStatus(HttpStatus.OK)
    public IndiceDTO revealIndice(@RequestParam Long visiteId) {
        Visite v = checkAndGetVisite(visiteId);
        Indice i = gameService.revealIndice(v);
        if(i == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No indice found");
        }
        return i.toDTO();
    }

    @PostMapping("/edit-status")
    @ResponseStatus(HttpStatus.OK)
    public StatutVisite editStatus(@RequestParam Long visiteId, @RequestParam StatutVisite status) {
        if (visiteRepository.findById(visiteId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visite not found");
        }
        Visite v = visiteRepository.findById(visiteId).get();
        try{
            gameService.changeStatusVisite(v, status);
            return status;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change to the given status");
        }
    }

    @GetMapping("/indice-cost")
    @ResponseStatus(HttpStatus.OK)
    public int getIndiceCost(@RequestParam Long visiteId) {
        Visite v = checkAndGetVisite(visiteId);
        return gameService.getIndiceCost(v);
    }



    @PostMapping("/create-default-defi")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createDefaultDefi() {
        Chami ch = new Chami("Suisse");
        ch.setBio("Je suis suisse");
        ch.setAge(158);
        ch.setIdGoogle("123456789");
        chamiRepository.save(ch);

        Arret a = new Arret();
        a.setCodeArret("SEM:0654");
        a.setVille("Saint-Martin-d'Hères");
        a.setNomArret("Bibliothèques Universitaires");
        a.setLongitude(5.77038);
        a.setLatitude(45.19166);

        arretRepository.save(a);

        Defi defi = new Defi();
        defi.setTitre("A la découverte des miagistes");
        defi.setMiniDescription("Vous devez découvrir les miagistes de la ville de Grenoble");
        defi.setDescription("Grasse à se defis vous decouvririrez les magnifiquement (moche) batiment de l'im2ag et vous pourré y observer une espéce animal trés etonante l'informatisien moyen");
        defi.setAuteur(ch);
        defi.setArret(a);
        defi.setDuree("10 minutes");


        //etape1
        Indication etape1 = new Indication();
        etape1.setTitre("A la découverte des miagistes");
        etape1.text = "Bienvenu à l’UFR IM2AG, le bâtiment, des geeks. Pour commencer, rentrez dans le bâtiment et passez à l’étape suivant dans la flèche de droite.";
        defi.addEtape(etape1);
        indicationRepository.save(etape1);
        //etape2
        Tache etape2 = new Tache();
        etape2.setTitre("Trouver Thomas");
        etape2.setDescription("Thomas, du BDE a perdu son bonnet. Pour l’aider, allez le voir dans la salle du BDE");
        etape2.setQuestion("Quel est le numéro de la salle du BDE ?");
        etape2.setSecret("110");
        etape2.setPoint(5);

        Indice indiceE21 = new Indice();
        indiceE21.setIndice("La salle est au premier etage");
        indiceE21.setPointsPerdus(1);

        Indice indiceE22 = new Indice();
        indiceE22.setIndice("Thomas est dans la salle 11x");
        indiceE22.setPointsPerdus(1);

        etape2.addIndice(indiceE21);
        etape2.addIndice(indiceE22);
        indiceRepository.save(indiceE21);
        indiceRepository.save(indiceE22);
        defi.addEtape(etape2);
        tacheRepository.save(etape2);
        //etape3GetMapping
        Indication etape3 = new Indication();
        etape3.setTitre("Parler à Thomas");
        etape3.text = "Salut, j’ai perdu mon magnifique bonnet miage, aide-moi à le retrouver. La dernière fois que je l’avais, j’étais dans la salle 218.";

        defi.addEtape(etape3);
        indicationRepository.save(etape3);
        //etape4
        Tache etape4 = new Tache();
        etape4.setTitre("Tu es branché 218 !");
        etape4.setDescription("Rend-toi dans la 218.");
        etape4.setQuestion("Combien y a-t-il de prise ? ");
        etape4.setSecret("97");
        etape4.setPoint(4);

        defi.addEtape(etape4);
        tacheRepository.save(etape4);
        defiRepository.save(defi);
    }

    private Visite checkAndGetVisite(Long visiteId) {
        if (visiteRepository.findById(visiteId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visite not found");
        }
        return visiteRepository.findById(visiteId).get();
    }

    @GetMapping("/get-visite")
    @ResponseStatus(HttpStatus.OK)
    public VisiteDTO getVisites(@RequestParam Long visiteId) {
        Visite visite = checkAndGetVisite(visiteId);
        return visite.toDTO();
    }


    @GetMapping("/get-visites-by-defi-chami/{idGoogle}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Set<VisiteDTO> getVisitesFinishedByChami(@PathVariable("idGoogle") final String idGoogle){
        var visites = visiteRepository.getVisitesFinishedByDefiAndChami(idGoogle);
        if (visites.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        Set<VisiteDTO> visitesDto = new HashSet<>();
        for(Visite v : visites.get()){
            var visite = v.toDTO();
            visitesDto.add(visite);
        }
        return visitesDto;
    }

    @GetMapping("/visite/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<VisiteDTO> getVisiteByUserIdStatus(@PathVariable("id") Long id, @PathVariable("status") StatutVisite status){
        var col = visiteRepository.findVisiteByUserAndStatus(status,id);
        return col.stream().map(Visite::toDTO).toList();
    }

}
