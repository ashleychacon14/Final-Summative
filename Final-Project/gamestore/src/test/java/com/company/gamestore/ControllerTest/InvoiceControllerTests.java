package com.company.gamestore.ControllerTest;

import com.company.gamestore.model.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@WebMvcTest(InvoiceControllerTests.class)
public class InvoiceControllerTests {
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private List<Invoice> invoiceList;

    @Before
    public void setUp(){

    }

    // Testing GET /records
    @Test
    public void shouldReturnAllInvoicesInCollection() throws Exception {

        // ARRANGE
        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(invoiceList);

        // ACT
        mockMvc.perform(get("/invoices"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }

    // Testing POST /records
    @Test
    public void shouldReturnNewInvoicesOnPostRequest() throws Exception {

        // ARRANGE
        Invoice inputInvoice = new Invoice();
        inputInvoice.setInvoice_id(150);
        inputInvoice.setName("X Company");
        inputInvoice.setStreet("333 Company Lane");
        inputInvoice.setCity("San Fransisco");

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(inputInvoice);

        Invoice outputInvoice = new Invoice();
        outputInvoice.setInvoice_id(150);
        outputInvoice.setName("X Company");
        outputInvoice.setStreet("333 Company Lane");
        outputInvoice.setCity("San Fransisco");
        outputInvoice.setState("CA");

        String outputJson = mapper.writeValueAsString(outputInvoice);

        // ACT
        mockMvc.perform(
                        post("/invoices")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());              // ASSERT (status code is 201)
    }

    // Testing GET record/{id}
    @Test
    public void shouldReturnInvoiceById() throws Exception {

        Invoice outputInvoice = new Invoice();
        outputInvoice.setInvoice_id(2);
        outputInvoice.setName("X Company");
        outputInvoice.setStreet("333 Company Lane");
        outputInvoice.setCity("San Fransisco");

        String outputJson = mapper.writeValueAsString(outputInvoice);

        mockMvc.perform(get("/invoices/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Testing PUT /records/{id}
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        Invoice inputInvoice = new Invoice();
        inputInvoice.setInvoice_id(2);
        inputInvoice.setName("X Company");
        inputInvoice.setStreet("333 Company Lane");
        inputInvoice.setCity("San Fransisco");

        String inputJson = mapper.writeValueAsString(inputInvoice);

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
        mockMvc.perform(delete("/consoles/5"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
