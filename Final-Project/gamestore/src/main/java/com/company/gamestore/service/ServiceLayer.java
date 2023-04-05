package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class ServiceLayer
{
    private final ConsoleRepository consoleRepository;
    private final GameRepository gameRepository;
    private final TshirtRepository tshirtRepository;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public ServiceLayer(ConsoleRepository consoleRepository,
                        GameRepository gameRepository,
                        TshirtRepository tshirtRepository,
                        InvoiceRepository invoiceRepository)
    {

        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
        this.tshirtRepository = tshirtRepository;
        this.invoiceRepository = invoiceRepository;
    }

    //================================ Saving ===============================

    public Console saveConsole(Console console) {

        return consoleRepository.save(console);
    }

    public Tshirt saveTshirt(Tshirt tshirt) {

        return tshirtRepository.save(tshirt);
    }

    public Game saveGame(Game game) {

        return gameRepository.save(game);
    }

    public Invoice saveInvoice(Invoice invoice)
    {
        return invoiceRepository.save(invoice);
    }

    //========================= Finding by ID ===============================

    public Console findConsoleById(Integer id) {
        Optional<Console> console = consoleRepository.findById(id);
        return console.orElse(null);
    }

    public Tshirt findTshirtById(Integer id) {
        Optional<Tshirt> tshirt = tshirtRepository.findById(id);
        return tshirt.orElse(null);
    }

    public Game findGameById(Integer id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);
    }

    public Invoice findInvoiceById(Integer id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        return invoice.orElse(null);
    }


    //==================== Finding all consoles ==========================

    public List<Console> findAllConsoles() {
        return consoleRepository.findAll();
    }

    public List<Tshirt> findAllTshirts()
    {
        return tshirtRepository.findAll();
    }

    public List<Game> findAllGames()
    {
        return gameRepository.findAll();
    }

    public List<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }


    //=================== Deleting by ID =============================

    public void deleteConsoleById(Integer id)
    {
        consoleRepository.deleteById(id);
    }

    public void deleteTshirtById(Integer id)
    {
        tshirtRepository.deleteById(id);
    }

    public void deleteGameById(Integer id)
    {
        gameRepository.deleteById(id);
    }

    public void deleteInvoiceById(Integer id)
    {
        invoiceRepository.deleteById(id);
    }



    //============== Custom Functions ======================
    //Console find all
    public List<Console> findAllConsolesByManufacturer(String manufacturer)
    {
        return consoleRepository.findAllByManufacturer(manufacturer);
    }

    //Game find all
    public List<Game> findAllGamesByStudio(String studio)
    {
        return gameRepository.findAllByStudio(studio);
    }

    public List<Game> findAllGamesByEsrbRating(String esrbRating)
    {
        return gameRepository.findAllByEsrb(esrbRating);
    }

    public List<Game> findAllGamesByTitle(String title)
    {
        return gameRepository.findAllByTitle(title);
    }

    //Invoice find all
//    ====================
//    public List<Invoice> findAllInvoicesByName(String name)
//    {
//        return invoiceRepository.findByCustomerName(name);
//    }

    //Tshirt find all
    public List<Tshirt> findAllTshirtsByColor(String color)
    {
        return tshirtRepository.findByColor(color);
    }

    public List<Tshirt> findAllByColor(String size)
    {
        return tshirtRepository.findBySize(size);
    }


}
