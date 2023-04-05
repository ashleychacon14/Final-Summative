package com.company.gamestore.RepositoryTest;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
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
public class InvoiceRepositoryTests {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Before
    public void setUp() throws Exception{
        invoiceRepository.deleteAll();
    }

    @Test
    public void addGetDeleteInvoice(){
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(150);
        invoice.setName("X Company");
        invoice.setStreet("333 Company Lane");
        invoice.setCity("San Fransisco");
        invoice.setState("CA");
        invoice.setZipcode("94016");
        invoice.setItem_type("Fancy");
        invoice.setItem_id(12);
        invoice.setUnit_price(new BigDecimal("1.50"));
        invoice.setQuantity(10);
        invoice.setSubtotal(new BigDecimal("0.12"));
        invoice.setTax(new BigDecimal("1.00"));
        invoice.setProcessing_fee(new BigDecimal("1.00"));
        invoice.setTotal(new BigDecimal("15.00"));

        invoice = invoiceRepository.save(invoice);

        Optional<Invoice> iList = invoiceRepository.findById(invoice.getInvoice_id());
        assertTrue(iList.isPresent());

    }

    @Test
    public void updateInvoice(){
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(150);
        invoice.setName("X Company");
        invoice.setStreet("333 Company Lane");
        invoice.setCity("San Fransisco");
        invoice.setState("CA");
        invoice.setZipcode("94016");
        invoice.setItem_type("Fancy");
        invoice.setItem_id(12);
        invoice.setUnit_price(new BigDecimal("1.50"));
        invoice.setQuantity(10);
        invoice.setSubtotal(new BigDecimal("0.12"));
        invoice.setTax(new BigDecimal("1.00"));
        invoice.setProcessing_fee(new BigDecimal("1.00"));
        invoice.setTotal(new BigDecimal("15.00"));

        invoice = invoiceRepository.save(invoice);

        invoice.setName("Ashley's Company");
        invoice.setStreet("444 Ashley Lane");
        invoice.setCity("El Paso");

        invoice = invoiceRepository.save(invoice);

        Optional<Invoice> cList = invoiceRepository.findById(invoice.getInvoice_id());
        assertEquals(cList.get(), invoice);
    }

    @Test
    public void getAllInvoices(){
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(150);
        invoice.setName("X Company");
        invoice.setStreet("333 Company Lane");
        invoice.setCity("San Fransisco");
        invoice.setState("CA");
        invoice.setZipcode("94016");
        invoice.setItem_type("Fancy");
        invoice.setItem_id(12);
        invoice.setUnit_price(new BigDecimal("1.50"));
        invoice.setQuantity(10);
        invoice.setSubtotal(new BigDecimal("0.12"));
        invoice.setTax(new BigDecimal("1.00"));
        invoice.setProcessing_fee(new BigDecimal("1.00"));
        invoice.setTotal(new BigDecimal("15.00"));

        invoice = invoiceRepository.save(invoice);

        invoice.setName("Ashley's Company");
        invoice.setStreet("444 Ashley Lane");
        invoice.setCity("El Paso");

        invoice = invoiceRepository.save(invoice);

        List<Invoice> cList = invoiceRepository.findAll();
        assertEquals(cList.size(), 1);
    }

}
