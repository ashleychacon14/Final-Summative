package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@RestController
public class ConsoleController {

    @Autowired
    ConsoleRepository repo;

    @Autowired
    public ConsoleController(ConsoleRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/consoles")
    public List<Console> getConsoles() {
        return repo.findAll();
    }

    @GetMapping("/consoles/{console_id}")
    public Console getConsoleById(@PathVariable int console_id) {

        Optional<Console> returnVal = repo.findById(console_id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/consoles/manufacturer/{manufacturer}")
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {

        List<Console> consoles = repo.findAllByManufacturer(manufacturer);

        // Convert the BigDecimal to string before returning
        consoles.forEach(console -> {
            console.setManufacturer(console.getManufacturer());
        });

        return consoles;
    }

    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody Console console) {
        // Convert the string price to BigDecimal
        console.setPrice(new BigDecimal(String.valueOf(console.getPrice())));

        return repo.save(console);
    }

    @PutMapping("/consoles/{console_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console) {
        // Convert the string price to BigDecimal
        console.setPrice(new BigDecimal(String.valueOf(console.getPrice())));

        repo.save(console);
    }

    @DeleteMapping("/consoles/{console_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int console_id) {
        repo.deleteById(console_id);
    }
}
