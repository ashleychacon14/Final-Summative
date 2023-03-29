package com.company.gamestore.controller;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class TshirtController {

    @Autowired
    TshirtRepository tShirtRepository;

    @PostMapping("/tshirt")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Tshirt addTShirt(@RequestBody Tshirt tShirt) {
        // Convert the string price to BigDecimal
        tShirt.setPrice(new BigDecimal(String.valueOf(tShirt.getPrice())));

        return tShirtRepository.save(tShirt);
    }

    @GetMapping("/tshirt/{id}")
    public Tshirt getTShirtById(@PathVariable int id) {
        Optional<Tshirt> returnVal = tShirtRepository.findById(id);

        // Convert the BigDecimal to string before returning
        returnVal.ifPresent(tShirt -> {
            tShirt.setPrice(tShirt.getPrice());
        });

        return returnVal.orElse(null);
    }

    @GetMapping("/tshirt")
    public List<Tshirt> getAllTShirts() {
        List<Tshirt> tShirts = tShirtRepository.findAll();

        // Convert the BigDecimal to string before returning
        tShirts.forEach(tShirt -> {
            tShirt.setPrice(tShirt.getPrice());
        });

        return tShirts;
    }

    @PutMapping("/tshirt/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody Tshirt tShirt) {
        // Convert the string price to BigDecimal
        tShirt.setPrice(new BigDecimal(String.valueOf(tShirt.getPrice())));

        tShirtRepository.save(tShirt);
    }

    @DeleteMapping("/tshirt/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable int id) {
        tShirtRepository.deleteById(id);
    }

    @GetMapping("/tshirt/color/{color}")
    public List<Tshirt> getTShirtsByColor(@PathVariable String color) {
        List<Tshirt> tShirts = tShirtRepository.findByColor(color);

        // Convert the BigDecimal to string before returning
        tShirts.forEach(tShirt -> {
            tShirt.setPrice(tShirt.getPrice());
        });

        return tShirts;
    }

    @GetMapping("/tshirt/size/{size}")
    public List<Tshirt> getTShirtsBySize(@PathVariable String size) {
        List<Tshirt> tShirts = tShirtRepository.findBySize(size);

        // Convert the BigDecimal to string before returning
        tShirts.forEach(tShirt -> {
            tShirt.setPrice(tShirt.getPrice());
        });

        return tShirts;
    }
}
