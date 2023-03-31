package com.company.gamestore.ControllerTest;

import com.company.gamestore.controller.InvoiceController;
import com.company.gamestore.model.Console;
import com.company.gamestore.model.Invoice;
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
@WebMvcTest(InvoiceTests.class)
public class InvoiceTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    InvoiceController invoiceController;

    ObjectMapper mapper = new ObjectMapper();

    // Add the JavaTimeModule to the ObjectMapper instance
    {
        mapper.registerModule(new JavaTimeModule());
    }

    List<Invoice> invoiceList;

    @Test
    public void shouldReturnCreatedStatus() throws Exception {
        Invoice inputInvoice = new Invoice();
        inputInvoice.setInvoice_id(150);
        inputInvoice.setName("X Company");
        inputInvoice.setStreet("333 Company Lane");
        inputInvoice.setCity("San Fransisco");

        ObjectNode invoiceNode = mapper.createObjectNode();
        invoiceNode.put("id", inputInvoice.getInvoice_id());
        invoiceNode.put("name", inputInvoice.getName());
        invoiceNode.put("street", inputInvoice.getStreet());
        invoiceNode.put("city", inputInvoice.getCity());

        mockMvc.perform(
                        post("/invoices")
                                .content(mapper.writeValueAsString(invoiceNode))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnOkStatus() throws Exception {
        Invoice inputInvoice = new Invoice();
        inputInvoice.setInvoice_id(150);
        inputInvoice.setName("X Company");
        inputInvoice.setStreet("333 Company Lane");
        inputInvoice.setCity("San Fransisco");

        ObjectNode invoiceNode = mapper.createObjectNode();
        invoiceNode.put("id", inputInvoice.getInvoice_id());
        invoiceNode.put("name", inputInvoice.getName());
        invoiceNode.put("street", inputInvoice.getStreet());

        mockMvc.perform(
                        get("/invoices/150")
                                .content(invoiceNode.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNoContentStatusOnUpdate() throws Exception {
        Invoice inputInvoice = new Invoice();
        inputInvoice.setInvoice_id(150);
        inputInvoice.setName("X Company");
        inputInvoice.setStreet("333 Company Lane");
        inputInvoice.setCity("San Fransisco");

        ObjectNode invoiceNode = mapper.createObjectNode();
        invoiceNode.put("id", inputInvoice.getInvoice_id());
        invoiceNode.put("name", inputInvoice.getName());
        invoiceNode.put("street", inputInvoice.getStreet());
        invoiceNode.put("city", inputInvoice.getCity());

        mockMvc.perform(
                        put("/invoices/150")
                                .content(invoiceNode.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnNoContentStatusOnDelete() throws Exception {
        Invoice inputInvoice = new Invoice();
        inputInvoice.setInvoice_id(150);
        inputInvoice.setName("X Company");
        inputInvoice.setStreet("333 Company Lane");
        inputInvoice.setCity("San Fransisco");

        String invoiceNode = mapper.writeValueAsString(inputInvoice);

        mockMvc.perform(
                        delete("/invoices/150")
                                .content(invoiceNode)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnOkStatusOnReadByCustomerName() throws Exception {
        Invoice inputInvoice = new Invoice();
        inputInvoice.setInvoice_id(150);
        inputInvoice.setName("X Company");
        inputInvoice.setStreet("333 Company Lane");
        inputInvoice.setCity("San Fransisco");

        String inputJson = mapper.writeValueAsString(inputInvoice);

        mockMvc.perform(
                        get("/invoices/customerName/X Company")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOkStatusOnReadAll() throws Exception {
        String invoiceNode = mapper.writeValueAsString(invoiceList);

        mockMvc.perform(
                        get("/invoices"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
