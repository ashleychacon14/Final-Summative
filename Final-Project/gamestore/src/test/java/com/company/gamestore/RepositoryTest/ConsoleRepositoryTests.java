package com.company.gamestore.RepositoryTest;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleRepositoryTests {
    @Autowired
    ConsoleRepository consoleRepository;

    @Before
    public void setUp() throws Exception{
        consoleRepository.deleteAll();
    }

    @Test
    public void addGetDeleteArtist(){
        Console console = new Console();
        console.setConsole_id(150);
        console.setModel("XBOX");
        console.setManufacturer("Flex");
        console.setMemory_amount("500 GB");
        console.setProcessor("Accelerated Processing Unit");
        console.setPrice(new BigDecimal("500.00"));
        console.setQuantity(10);

        console = consoleRepository.save(console);

        List<Console> cList = consoleRepository.findAll();
        assertEquals(cList.size(), 2);

    }

    @Test
    public void updateConsole(){
        Console console = new Console();
        console.setConsole_id(150);
        console.setModel("XBOX");
        console.setManufacturer("Flex");
        console.setMemory_amount("500 GB");
        console.setProcessor("Accelerated Processing Unit");
        console.setPrice(new BigDecimal("500.00"));
        console.setQuantity(10);

        console = consoleRepository.save(console);

        console.setModel("PS5");
        console.setManufacturer("Sony");

        console = consoleRepository.save(console);

        Optional<Console> cList = consoleRepository.findById(console.getConsole_id());
        assertEquals(cList.get(), console);

    }

    @Test
    public void getAllConsoles(){
        Console console = new Console();
        console.setConsole_id(150);
        console.setModel("XBOX");
        console.setManufacturer("Flex");
        console.setMemory_amount("500 GB");
        console.setProcessor("Accelerated Processing Unit");
        console.setPrice(new BigDecimal("500.00"));
        console.setQuantity(10);

        console = consoleRepository.save(console);

        console = new Console();
        console.setModel("PS5");
        console.setManufacturer("Sony");

        console = consoleRepository.save(console);

        List<Console> cList = consoleRepository.findAll();
        assertEquals(cList.size(), 2);

    }

}
