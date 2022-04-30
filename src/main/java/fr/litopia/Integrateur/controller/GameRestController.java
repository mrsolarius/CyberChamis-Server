package fr.litopia.Integrateur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.litopia.Integrateur.services.GameService;

@RestController
@RequestMapping(value="/api/game",produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameRestController {
    @Autowired
    private GameService gameService;
}
