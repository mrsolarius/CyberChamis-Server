package fr.litopia.Integrateur.controller;

import fr.litopia.Integrateur.model.dto.EtapeDTO;
import fr.litopia.Integrateur.model.dto.IndiceDTO;
import fr.litopia.Integrateur.model.dto.VisiteDTO;
import fr.litopia.Integrateur.model.entity.*;
import fr.litopia.Integrateur.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import fr.litopia.Integrateur.services.GameService;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
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
    private EtapeRepository etapeRepository;

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
        Visite v = gameService.commencerVisite(defi, user);
        return v.toDTO();
    }


    @PostMapping("/next-etape")
    @ResponseStatus(HttpStatus.OK)
    public EtapeDTO etapeSuivante(@RequestParam Long visiteId) {
        if (visiteRepository.findById(visiteId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visite not found");
        }
        Visite v = visiteRepository.findById(visiteId).get();
        try {
            Etape e = gameService.etapeSuivante(v);
            return e.toDTO();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.GONE, "No more etape");
        }
    }

    @PostMapping("/previsous-etape")
    @ResponseStatus(HttpStatus.OK)
    public EtapeDTO etapePrecedente(@RequestParam Long visiteId) {
        if (visiteRepository.findById(visiteId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visite not found");
        }
        Visite v = visiteRepository.findById(visiteId).get();
        try {
            Etape e = gameService.etapePrecedente(v);
            return e.toDTO();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.GONE, "No more etape");
        }
    }

    @PostMapping("/check-response")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkResponse(@RequestParam Long visiteId, @RequestParam String response) {
        if (visiteRepository.findById(visiteId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visite not found");
        }
        Visite v = visiteRepository.findById(visiteId).get();
        try {
            return gameService.checkResponse(response,v);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad response");
        }
    }

    @GetMapping("/response-indice")
    @ResponseStatus(HttpStatus.OK)
    public List<IndiceDTO> getResponseIndices(@RequestParam Long visiteId) {
        if (visiteRepository.findById(visiteId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visite not found");
        }
        Visite v = visiteRepository.findById(visiteId).get();
        List<Indice> indiceList = gameService.getIndices(v);
        if(indiceList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No indice found");
        }
        return indiceList.stream().map(Indice::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/reveal-indice")
    @ResponseStatus(HttpStatus.OK)
    public IndiceDTO revealIndice(@RequestParam Long visiteId) {
        if (visiteRepository.findById(visiteId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visite not found");
        }
        Visite v = visiteRepository.findById(visiteId).get();
        Indice i = gameService.revealIndice(v);
        if(i == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No indice found");
        }
        return i.toDTO();
    }

    @PostMapping("edit-status")
    @ResponseStatus(HttpStatus.OK)
    public Visite editStatus(@RequestParam Long visiteId, @RequestParam StatutVisite status) {
        if (visiteRepository.findById(visiteId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Visite not found");
        }
        Visite v = visiteRepository.findById(visiteId).get();
        try{
            return gameService.changeStatusVisite(v, status);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change to the given status");
        }
    }




    @PostMapping("/create-default-defi")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createDefaultDefi() {
        Chami ch = new Chami("Suisse");
        ch.setBio("Je suis suisse");
        ch.setAge(158);
        chamiRepository.save(ch);

        Arret a = new Arret();
        a.setCodeArret("A001");
        a.setNomArret("Bibiliotheque");
        a.setStreetMap("https://www.openstreetmap.org/#map=19/45.19240/5.77088");
        arretRepository.save(a);

        Defi defi = new Defi();
        defi.setTitre("A la découverte des miagistes");
        defi.setDescription("Vous devez découvrir les miagistes de la ville de Grenoble");
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
        etape2.setSecret("111");
        etape2.setPoint(5);

        Indice indiceE21 = new Indice();
        indiceE21.setIndice("La salle est au premier etage");
        indiceE21.setPointsPerdus(1);
        indiceRepository.save(indiceE21);

        Indice indiceE22 = new Indice();
        indiceE22.setIndice("Thomas est dans la salle 11x");
        indiceE22.setPointsPerdus(1);
        indiceRepository.save(indiceE22);

        etape2.addIndice(indiceE21);
        etape2.addIndice(indiceE22);
        defi.addEtape(etape2);
        tacheRepository.save(etape2);
        //etape3
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

}
