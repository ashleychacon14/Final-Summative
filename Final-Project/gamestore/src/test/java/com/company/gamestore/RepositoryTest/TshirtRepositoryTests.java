package com.company.gamestore.RepositoryTest;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@SpringJUnitConfig
public class TshirtRepositoryTests {

    @Autowired
    private TshirtRepository tshirtRepository;

    @Test
    public void shouldSaveTShirtToDatabase() {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("blue");
        tshirt.setSize("L");

        Tshirt savedTshirt = tshirtRepository.save(tshirt);

        assertThat(savedTshirt.getId()).isNotNull();
        assertThat(savedTshirt.getColor()).isEqualTo(tshirt.getColor());
        assertThat(savedTshirt.getSize()).isEqualTo(tshirt.getSize());
    }

    @Test
    public void shouldFindTShirtById() {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("red");
        tshirt.setSize("M");

        Tshirt savedTshirt = tshirtRepository.save(tshirt);

        Tshirt foundTshirt = tshirtRepository.findById(savedTshirt.getId()).orElse(null);

        assertThat(foundTshirt).isNotNull();
        assertThat(foundTshirt.getId()).isEqualTo(savedTshirt.getId());
        assertThat(foundTshirt.getColor()).isEqualTo(savedTshirt.getColor());
        assertThat(foundTshirt.getSize()).isEqualTo(savedTshirt.getSize());
    }

    @Test
    public void shouldFindAllTShirts() {
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setColor("green");
        tshirt1.setSize("S");

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("blue");
        tshirt2.setSize("M");

        Tshirt tshirt3 = new Tshirt();
        tshirt3.setColor("red");
        tshirt3.setSize("L");

        tshirtRepository.save(tshirt1);
        tshirtRepository.save(tshirt2);
        tshirtRepository.save(tshirt3);

        List<Tshirt> tshirts = tshirtRepository.findAll();

        assertThat(tshirts).hasSize(3);
        assertThat(tshirts).contains(tshirt1, tshirt2, tshirt3);
    }

    @Test
    public void shouldUpdateTShirt() {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("black");
        tshirt.setSize("S");

        Tshirt savedTshirt = tshirtRepository.save(tshirt);

        savedTshirt.setColor("white");

        Tshirt updatedTshirt = tshirtRepository.save(savedTshirt);

        assertThat(updatedTshirt.getId()).isEqualTo(savedTshirt.getId());
        assertThat(updatedTshirt.getColor()).isEqualTo("white");
        assertThat(updatedTshirt.getSize()).isEqualTo(savedTshirt.getSize());
    }

    @Test
    public void shouldReturnTShirtsBySize() {
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setColor("red");
        tshirt1.setSize("M");

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("blue");
        tshirt2.setSize("L");

        tshirtRepository.save(tshirt1);
        tshirtRepository.save(tshirt2);

        List<Tshirt> tshirtsBySize = tshirtRepository.findBySize("M");

        assertThat(tshirtsBySize.size()).isEqualTo(1);
        assertThat(tshirtsBySize.get(0).getColor()).isEqualTo("red");
    }
}