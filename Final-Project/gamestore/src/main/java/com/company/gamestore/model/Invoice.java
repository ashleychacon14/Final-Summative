package com.company.gamestore.model;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice")
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int invoice_id;
    @Column(name="name")
    String name;
    @Column(name="street")
    String street;
    @Column(name="city")
    String city;
    @Column(name="state")
    String state;
    @Column(name="zipcode")
    String zipcode;
    @Column(name="item_type")
    String item_type;
    @Column(name="item_id")
    int item_id;
    @Column(name="unit_price")
    BigDecimal unit_price;
    @Column(name="quantity")
    int quantity;
    @Column(name="subtotal")
    BigDecimal subtotal;
    @Column(name="tax")
    BigDecimal tax;
    @Column(name="processing_fee")
    BigDecimal processing_fee;
    @Column(name="total")
    BigDecimal total;

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessing_fee() {
        return processing_fee;
    }

    public void setProcessing_fee(BigDecimal processing_fee) {
        this.processing_fee = processing_fee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return quantity == invoice.quantity && Objects.equals(invoice_id, invoice.invoice_id) && Objects.equals(name, invoice.name) && Objects.equals(street, invoice.street)
                && Objects.equals(city, invoice.city) && Objects.equals(zipcode, invoice.zipcode) && Objects.equals(item_type, invoice.item_type) && Objects.equals(item_id, invoice.item_id)
                && Objects.equals(unit_price, invoice.unit_price) && Objects.equals(subtotal, invoice.subtotal) && Objects.equals(processing_fee, invoice.processing_fee)
                && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id, name, street, city, zipcode, item_type, item_id, unit_price,quantity, subtotal ,tax, processing_fee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + invoice_id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipcode=" + zipcode +
                ", item_type=" + item_type +
                ", item_id='" + item_id + '\'' +
                ", unit_price='" + unit_price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", tax='" + tax + '\'' +
                ", processing_fee='" + processing_fee + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
