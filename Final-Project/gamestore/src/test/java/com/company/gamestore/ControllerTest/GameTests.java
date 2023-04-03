package com.company.gamestore.ControllerTest;

import com.company.gamestore.controller.GameController;
import com.company.gamestore.model.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GameTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GameController gameController;

    ObjectMapper objectMapper = new ObjectMapper();

    // Add the JavaTimeModule to the ObjectMapper instance
    {
        objectMapper.registerModule(new JavaTimeModule());
    }

    List<Game> gameList;

    @Test
    public void shouldReturnCreatedStatus() throws Exception {
        Game game = new Game();
        game.setTitle("Destiny");
        game.setGame_id(0);
        game.setStudio("Activision");
        game.setPrice(new BigDecimal("19.99"));
        game.setQuantity(4);
        game.setEsrb("E");

        ObjectNode gameNode = objectMapper.createObjectNode();
        gameNode.put("title", game.getTitle());
        gameNode.put("studio", game.getStudio());
        gameNode.put("price", game.getPrice());
        gameNode.put("quantity", game.getQuantity());
        gameNode.put("esrb_rating", game.getEsrb());
        gameNode.put("id",game.getGame_id());



        mockMvc.perform(
                        post("/games")
                                .content(objectMapper.writeValueAsString(gameNode))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnOkStatus() throws Exception {
        Game game = new Game();
        game.setTitle("Destiny");
        game.setGame_id(1);
        game.setStudio("Activision");
        game.setPrice(new BigDecimal("19.99"));
        game.setQuantity(4);
        game.setEsrb("E");

        ObjectNode gameNode = objectMapper.createObjectNode();
        gameNode.put("title", game.getTitle());
        gameNode.put("studio", game.getStudio());
        gameNode.put("price", game.getPrice());
        gameNode.put("quantity", game.getQuantity());
        gameNode.put("esrb_rating", game.getEsrb());
        gameNode.put("id",game.getGame_id());
        mockMvc.perform(
                        get("/games/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNoContentStatusOnUpdate() throws Exception {
        Game game = new Game();
        game.setTitle("Destiny");
        game.setGame_id(0);
        game.setStudio("Activision");
        game.setPrice(new BigDecimal("19.99"));
        game.setQuantity(4);
        game.setEsrb("E");
        ObjectNode gameNode = objectMapper.createObjectNode();
        mockMvc.perform(
                        put("/games/1")
                                .content(gameNode.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldReturnNoContentStatusOnDelete() throws Exception {
        Game game = new Game();
        game.setTitle("Destiny");
        game.setGame_id(0);
        game.setStudio("Activision");
        game.setPrice(new BigDecimal("19.99"));
        game.setQuantity(4);
        game.setEsrb("E");
        String inputJson = objectMapper.writeValueAsString(game);

        mockMvc.perform(
                        delete("/games/1")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldReturnOkStatusOnReadByEsrbRating() throws Exception {
        Game inputGame = new Game();
        Game game = new Game();
        game.setTitle("Destiny");
        game.setGame_id(0);
        game.setStudio("Activision");
        game.setPrice(new BigDecimal("19.99"));
        game.setQuantity(4);
        game.setEsrb("E");

        String inputJson = objectMapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        get("/games/esrb_rating/E")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldReturnOkStatusOnReadByStudio() throws Exception {
        Game inputGame = new Game();
        Game game = new Game();
        game.setTitle("Destiny");
        game.setGame_id(0);
        game.setStudio("Activision");
        game.setPrice(new BigDecimal("19.99"));
        game.setQuantity(4);
        game.setEsrb("E");

        String inputJson = objectMapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        get("/games/studio/Activision")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatusOnReadByTitle() throws Exception {
        Game inputGame = new Game();
        Game game = new Game();
        game.setTitle("Destiny");
        game.setGame_id(0);
        game.setStudio("Activision");
        game.setPrice(new BigDecimal("19.99"));
        game.setQuantity(4);
        game.setEsrb("E");

        String inputJson = objectMapper.writeValueAsString(inputGame);

        mockMvc.perform(
                        get("/games/title/Destiny")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
