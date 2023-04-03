package com.company.gamestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.TshirtRepository;
import com.company.gamestore.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
public class GraphController {

    @Autowired
    ConsoleRepository consoleRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    TshirtRepository tshirtRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    @QueryMapping
    public Game findGameById(@Argument int game_id) {
        return gameRepository.getById(game_id);
    }
    @QueryMapping
    public Invoice findInvoiceById(@Argument int item_id) {
        return invoiceRepository.getById(item_id);
    }
    @QueryMapping
    public Tshirt findTshirtById(@Argument int id) {
        return tshirtRepository.getById(id);
    }

    @QueryMapping
    public Console findConsoleById(@Argument int console_id) {
        return consoleRepository.getById(console_id);
    }

    @QueryMapping
    public List<Game> getAllGames() {
        return  gameRepository.findAll();
    }

    @QueryMapping
    public List<Console> getAllConsoles() {
        return  consoleRepository.findAll();
    }

    @QueryMapping
    public List<Game> findGameByTitle(@Argument String title) {
        return gameRepository.findAllByTitle(title);
    }

    @QueryMapping
    public List<Game> findGameByStudio(@Argument String studio) {
        return gameRepository.findAllByStudio(studio);
    }

    @QueryMapping
    public List<Game> findGameByEsrb(@Argument String esrb) {
        return gameRepository.findAllByEsrb(esrb);
    }

    @QueryMapping
    public List<Console> findConsoleByManufacturer(@Argument String manufacturer) {
        return consoleRepository.findAllByManufacturer(manufacturer);
    }
}




