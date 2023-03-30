package com.company.gamestore.ControllerTest;

import com.company.gamestore.model.Console;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleControllerTests.class)
public class ConsoleControllerTests {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();
    private List<Console> consoleList;

    @Before
    public void setUp(){

    }

    @Test
    public void shouldReturnAllConsolesInCollections() throws Exception{
        // ARRANGE
        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(consoleList);

        // ACT
        mockMvc.perform(get("/consoles"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }

    @Test
    public void shouldReturnNewConsoleOnPostRequest() throws Exception {
        // ARRANGE
        Console inputConsole = new Console();
        inputConsole.setConsole_id(150);
        inputConsole.setModel("XBOX");
        inputConsole.setManufacturer("Flex");
        inputConsole.setMemory_amount("500 GB");

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(inputConsole);

        Console outputConsole = new Console();
        outputConsole.setConsole_id(150);
        outputConsole.setModel("XBOX");
        outputConsole.setManufacturer("Flex");
        outputConsole.setMemory_amount("500 GB");
        outputConsole.setProcessor("Accelerated Processing Unit");

        String outputJson = mapper.writeValueAsString(outputConsole);

        // ACT
        mockMvc.perform(
                        post("/consoles")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());              // ASSERT (status code is 201)
    }

    // Testing GET record/{id}
    @Test
    public void shouldReturnConsoleById() throws Exception {

        Console inputConsole = new Console();
        inputConsole.setConsole_id(2);
        inputConsole.setModel("XBOX");
        inputConsole.setManufacturer("Flex");
        inputConsole.setMemory_amount("500 GB");

        String outputJson = mapper.writeValueAsString(inputConsole);

        mockMvc.perform(get("/consoles/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Testing PUT /records/{id}
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        Console inputConsole = new Console();
        inputConsole.setConsole_id(2);
        inputConsole.setModel("XBOX");
        inputConsole.setManufacturer("Flex");
        inputConsole.setMemory_amount("500 GB");

        String inputJson = mapper.writeValueAsString(inputConsole);

        mockMvc.perform(
                        put("/consoles/2")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /records/{id}
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content
        mockMvc.perform(delete("/consoles/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


}
