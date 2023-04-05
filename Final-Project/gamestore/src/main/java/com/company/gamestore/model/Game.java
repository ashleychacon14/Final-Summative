package com.company.gamestore.model;
import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game")
public class Game implements Serializable {

    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int game_id;


    @NotNull
    private String title;

    @NotNull
    private String esrb;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String studio;

    @NotNull
    private int quantity;


    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrb() {
        return esrb;
    }

    public void setEsrb(String esrb) {
        this.esrb = esrb;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
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
        Game game = (Game) o;
        return Objects.equals(game.getGame_id(), game.getGame_id()) &&
                Objects.equals(getTitle(), game.getTitle()) &&
                Objects.equals(getEsrb(), game.getEsrb()) &&
                Objects.equals(getPrice(), game.getPrice()) &&
                Objects.equals(getStudio(), game.getStudio()) &&
                Objects.equals(getQuantity(), game.getQuantity());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getGame_id(),
                getTitle(),
                getEsrb(),
                getPrice(),
                getStudio(),
                getQuantity());
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + game_id +
                ", title='" + title + '\'' +
                ", esrb='" + esrb + '\'' +
                ", price='" + price + '\'' +
                ", studio='" + studio + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}