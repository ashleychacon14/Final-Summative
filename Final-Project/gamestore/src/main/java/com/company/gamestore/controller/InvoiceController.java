package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.InvoiceRepository;

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
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        return repo.save(invoice);
    }

    @PutMapping("/invoices")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody Invoice invoice) {
        repo.save(invoice);
    }

    @DeleteMapping("/invoices/{invoice_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int invoice_id) {
        repo.deleteById(invoice_id);
    }
}
