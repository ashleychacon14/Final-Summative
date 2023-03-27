package com.company.gamestore.controller;

import java.util.List;
import java.util.Optional;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GameController {
    private final GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/games")
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/games/{game_id}")
    public Game getGameById(@PathVariable int gameId) {
        Optional<Game> returnVal = gameRepository.findById(gameId);
        return returnVal.orElse(null);
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game) {
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