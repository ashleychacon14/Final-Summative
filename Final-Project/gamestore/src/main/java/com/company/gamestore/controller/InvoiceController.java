package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.InvoiceRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceRepository repo;

    @Autowired
    public InvoiceController(InvoiceRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/invoices")
    public List<Invoice> getInvoices() {
        return repo.findAll();
    }

    @GetMapping("/invoices/{invoice_id}")
    public Invoice getInvoiceById(@PathVariable int invoice_id) {

        Optional<Invoice> returnVal = repo.findById(invoice_id);
        // Convert the BigDecimal to string before returning
        returnVal.ifPresent(invoice -> {
            invoice.setInvoice_id(invoice.getInvoice_id());
        });

        return returnVal.orElse(null);
    }

    @GetMapping("/invoices/customerName/{name}")
    public List<Invoice> getInvoiceByName(@PathVariable String name) {
        List<Invoice> invoices = repo.findByName(name);

        // Convert the BigDecimal to string before returning
        invoices.forEach(invoice -> {
            invoice.setName(invoice.getName());
        });

        return invoices;
    }

    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        invoice.setProcessing_fee(new BigDecimal(String.valueOf(invoice.getProcessing_fee())));

        return repo.save(invoice);
    }

    @PutMapping("/invoices/{invoice_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody Invoice invoice) {
        invoice.setProcessing_fee(new BigDecimal(String.valueOf(invoice.getProcessing_fee())));

        repo.save(invoice);
    }

    @DeleteMapping("/invoices/{invoice_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int invoice_id) {
        repo.deleteById(invoice_id);
    }
}
