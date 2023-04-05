package com.company.gamestore.RepositoryTest;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameRepositoryTests {
    @Autowired
    GameRepository gameRepository;

    @Before
    public void setUp() throws Exception{
        gameRepository.deleteAll();
    }

    @Test
    public void addGetDeleteInvoice(){
        Game game = new Game();
        game.setGame_id(150);
        game.setTitle("Destiny");
        game.setStudio("Activision");
        game.setEsrb("E");
        game.setQuantity(3);
        game.setPrice(new BigDecimal("5.12"));
        game= gameRepository.save(game);

        Optional<Game> iList = gameRepository.findById(game.getGame_id());
        assertTrue(iList.isPresent());

    }

    @Test
    public void updateInvoice(){
        Game game = new Game();
        game.setGame_id(150);
        game.setTitle("Destiny");
        game.setStudio("Activision");
        game.setEsrb("E");
        game.setQuantity(3);
        game.setPrice(new BigDecimal("5.12"));

        game= gameRepository.save(game);
        game.setTitle("Infamous");

        game = gameRepository.save(game);

        Optional<Game> cList = gameRepository.findById(game.getGame_id());
        assertEquals(cList.get(), game);
    }

    @Test
    public void getAllInvoices(){
        Game game = new Game();
        game.setGame_id(150);
        game.setTitle("Destiny");
        game.setStudio("Activision");
        game.setEsrb("E");
        game.setQuantity(3);
        game.setPrice(new BigDecimal("5.12"));

        game= gameRepository.save(game);
        game.setTitle("Infamous");

        game = gameRepository.save(game);


        List<Game> cList = gameRepository.findAll();
        assertEquals(cList.size(), 1);
    }

}