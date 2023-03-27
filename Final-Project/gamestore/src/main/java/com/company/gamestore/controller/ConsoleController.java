package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody Console console) {
        return repo.save(console);
    }

    @PutMapping("/consoles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console) {
        repo.save(console);
    }

    @DeleteMapping("/consoles/{console_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int console_id) {
        repo.deleteById(console_id);
    }
}
