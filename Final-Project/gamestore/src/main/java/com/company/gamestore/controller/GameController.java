package com.company.gamestore.controller;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GameController {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/games")
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/games/{game_id}")
    public Optional <Game> getGameById(@PathVariable int game_id) {
        Optional <Game> returnVal = gameRepository.findById(game_id);
        if (returnVal == null) {
            throw new IllegalArgumentException("game not found");
        } else {
            return Optional.of(returnVal.get());
        }
    }

    @GetMapping("/games/studio/{studio}")
    public List<Game> getGameByStudio(@PathVariable String studio) {

        List<Game> studios = gameRepository.findAllByStudio(studio);

        // Convert the BigDecimal to string before returning
        studios.forEach(studio1 -> {
            studio1.setStudio(studio1.getStudio());
        });

        return studios;
    }

    @GetMapping("/games/esrb_rating/{rating}")
    public List<Game> getGameByEsbrRating(@PathVariable String rating) {

        List<Game> esbrRatings = gameRepository.findAllByStudio(rating);

        // Convert the BigDecimal to string before returning
        esbrRatings.forEach(esbrRating -> {
            esbrRating.setEsrb(esbrRating.getEsrb());
        });

        return esbrRatings;
    }

    @GetMapping("/games/title/{title}")
    public List<Game> getGameByTitle(@PathVariable String title) {

        List<Game> titles = gameRepository.findAllByStudio(title);

        // Convert the BigDecimal to string before returning
        titles.forEach(title1 -> {
            title1.setTitle(title1.getTitle());
        });

        return titles;
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody Game game) {
        // Convert the string price to BigDecimal
        game.setPrice(new BigDecimal(String.valueOf(game.getPrice())));

        return gameRepository.save(game);
    }

    @PutMapping("/games/{game_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game) {
        // Convert the string price to BigDecimal
        game.setPrice(new BigDecimal(String.valueOf(game.getPrice())));

        gameRepository.save(game);
    }

    @DeleteMapping("/games/{game_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int game_id) {
        try {
            gameRepository.deleteById(game_id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found", ex);
        }
    }
}