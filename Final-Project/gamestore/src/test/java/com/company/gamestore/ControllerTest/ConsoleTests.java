package com.company.gamestore.ControllerTest;

import com.company.gamestore.controller.ConsoleController;
import com.company.gamestore.model.Console;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleTests.class)
public class ConsoleTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ConsoleController consoleController;

    ObjectMapper mapper = new ObjectMapper();

    // Add the JavaTimeModule to the ObjectMapper instance
    {
        mapper.registerModule(new JavaTimeModule());
    }
    List<Console> consoleList;

    @Test
    public void shouldReturnCreatedStatus() throws Exception {
        Console inputConsole = new Console();
        inputConsole.setConsole_id(2);
        inputConsole.setModel("XBOX");
        inputConsole.setManufacturer("Flex");
        inputConsole.setMemory_amount("500 GB");

        ObjectNode consoleNode = mapper.createObjectNode();
        consoleNode.put("console id", inputConsole.getConsole_id());
        consoleNode.put("model", inputConsole.getModel());
        consoleNode.put("manufacturer", inputConsole.getManufacturer());
        consoleNode.put("memory amount", inputConsole.getMemory_amount());

        mockMvc.perform(
                        post("/consoles")
                                .content(mapper.writeValueAsString(consoleNode))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnOkStatus() throws Exception {
        Console inputConsole = new Console();
        inputConsole.setConsole_id(2);
        inputConsole.setModel("XBOX");
        inputConsole.setManufacturer("Flex");
        inputConsole.setMemory_amount("500 GB");

        ObjectNode consoleNode = mapper.createObjectNode();
        consoleNode.put("console id", inputConsole.getConsole_id());
        consoleNode.put("model", inputConsole.getModel());
        consoleNode.put("manufacturer", inputConsole.getManufacturer());
        consoleNode.put("memory amount", inputConsole.getMemory_amount());

        mockMvc.perform(
                        get("/consoles/2")
                                .content(consoleNode.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNoContentStatusOnUpdate() throws Exception {
        Console inputConsole = new Console();
        inputConsole.setConsole_id(2);
        inputConsole.setModel("XBOX");
        inputConsole.setManufacturer("Flex");
        inputConsole.setMemory_amount("500 GB");

        ObjectNode consoleNode = mapper.createObjectNode();
        consoleNode.put("console id", inputConsole.getConsole_id());
        consoleNode.put("model", inputConsole.getModel());
        consoleNode.put("manufacturer", inputConsole.getManufacturer());
        consoleNode.put("memory amount", inputConsole.getMemory_amount());

        mockMvc.perform(
                        put("/consoles/2")
                                .content(consoleNode.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnNoContentStatusOnDelete() throws Exception {
        Console inputConsole = new Console();
        inputConsole.setConsole_id(2);
        inputConsole.setModel("XBOX");
        inputConsole.setManufacturer("Flex");
        inputConsole.setMemory_amount("500 GB");

        String inputJson = mapper.writeValueAsString(inputConsole);

        mockMvc.perform(
                        delete("/consoles/2")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnOkStatusOnReadByManufacturer() throws Exception {
        Console inputConsole = new Console();
        inputConsole.setConsole_id(2);
        inputConsole.setModel("XBOX");
        inputConsole.setManufacturer("Flex");
        inputConsole.setMemory_amount("500 GB");

        String inputJson = mapper.writeValueAsString(inputConsole);

        mockMvc.perform(
                        get("/consoles/manufacturer/Flex")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatusOnReadAll() throws Exception {
        String outputJson = mapper.writeValueAsString(consoleList);

        mockMvc.perform(
                        get("/consoles"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
