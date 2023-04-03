package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class Console implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int console_id;
    @Column(name="model")
    private String model;
    @Column(name="manufacturer")
    private String manufacturer;
    @Column(name="memory_amount")
    private String memory_amount;
    @Column(name="processor")
    private String processor;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="quantity")
    private int quantity;

    // @OneToMany(mappedBy = "console_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private List<Game> games;

    // public List<Game> getGames(){
    //   return games;
    //  }

    // public List<Console> consoles;

    // public List<Console> getConsoles(){
    //    return consoles;
    // }

    public int getConsole_id() {
        return console_id;
    }

    public void setConsole_id(int console_id) {
        this.console_id = console_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemory_amount() {
        return memory_amount;
    }

    public void setMemory_amount(String memory_amount) {
        this.memory_amount = memory_amount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return manufacturer == console.manufacturer && Objects.equals(console_id, console.console_id) && Objects.equals(model, console.model) && Objects.equals(memory_amount, console.memory_amount)
                && Objects.equals(processor, console.processor) && Objects.equals(price, console.price) && Objects.equals(quantity, console.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(console_id, model, manufacturer, memory_amount, processor, price, quantity);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + console_id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memory_amount='" + memory_amount + '\'' +
                ", processor=" + processor +
                ", price=" + price +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
