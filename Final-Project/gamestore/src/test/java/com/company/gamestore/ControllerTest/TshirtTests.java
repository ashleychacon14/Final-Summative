package com.company.gamestore.ControllerTest;

import com.company.gamestore.controller.TshirtController;
import com.company.gamestore.model.Tshirt;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(TshirtController.class)
public class TshirtTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TshirtController tshirtController;

    ObjectMapper objectMapper = new ObjectMapper();

    // Add the JavaTimeModule to the ObjectMapper instance
    {
        objectMapper.registerModule(new JavaTimeModule());
    }

    List<Tshirt> tshirtList;

    @Test
    public void shouldReturnCreatedStatus() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("M");
        tshirt.setColor("Blue");
        tshirt.setDescription("Comfortable tshirt");
        tshirt.setPrice(new BigDecimal("19.99"));

        ObjectNode tshirtNode = objectMapper.createObjectNode();
        tshirtNode.put("size", tshirt.getSize());
        tshirtNode.put("color", tshirt.getColor());
        tshirtNode.put("description", tshirt.getDescription());
        tshirtNode.put("price", tshirt.getPrice());

        mockMvc.perform(
                        post("/tshirt")
                                .content(objectMapper.writeValueAsString(tshirtNode))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnOkStatus() throws Exception {
        Tshirt inputTshirt = new Tshirt();
        inputTshirt.setId(0);
        inputTshirt.setSize("M");
        inputTshirt.setColor("Blue");

        ObjectNode inputJson = objectMapper.createObjectNode();
        inputJson.put("tshirtId", inputTshirt.getId());
        inputJson.put("size", inputTshirt.getSize());
        inputJson.put("color", inputTshirt.getColor());

        mockMvc.perform(
                        get("/tshirt/0")
                                .content(inputJson.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNoContentStatusOnUpdate() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setDescription("Comfortable t-shirt");
        tshirt.setSize("M");
        tshirt.setPrice(new BigDecimal("15.99"));

        ObjectNode inputNode = objectMapper.createObjectNode();
        inputNode.put("color", tshirt.getColor());
        inputNode.put("description", tshirt.getDescription());
        inputNode.put("size", tshirt.getSize());
        inputNode.put("price", tshirt.getPrice().toString());

        mockMvc.perform(
                        put("/tshirt/1")
                                .content(inputNode.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldReturnNoContentStatusOnDelete() throws Exception {
        Tshirt tShirt = new Tshirt();
        tShirt.setColor("Red");
        tShirt.setDescription("Comfortable t-shirt");
        tShirt.setSize("M");
        tShirt.setPrice(new BigDecimal("15.99"));
        tShirt.setId(1);

        String inputJson = objectMapper.writeValueAsString(tShirt);

        mockMvc.perform(
                        delete("/tshirt/1")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldReturnOkStatusOnReadByColor() throws Exception {
        Tshirt inputTShirt = new Tshirt();
        inputTShirt.setColor("Red");

        String inputJson = objectMapper.writeValueAsString(inputTShirt);

        mockMvc.perform(
                        get("/tshirt/color/Red")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldReturnOkStatusOnReadBySize() throws Exception {
        Tshirt inputTShirt = new Tshirt();
        inputTShirt.setSize("M");

        String inputJson = objectMapper.writeValueAsString(inputTShirt);

        mockMvc.perform(
                        get("/tshirt/size/M")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldReturnOkStatusOnReadAll() throws Exception {
        String outputJson = objectMapper.writeValueAsString(tshirtList);

        mockMvc.perform(
                        get("/tshirt"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
