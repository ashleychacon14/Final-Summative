package com.company.gamestore.RepositoryTest;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class TshirtRepositoryTests {
    @Autowired
    private TshirtRepository tShirtRepository;

    @Test
    public void shouldSaveTShirtToDatabase() {
        // Given
        Tshirt tShirt = new Tshirt();
        tShirt.setColor("blue");
        tShirt.setSize("L");

        // When
        Tshirt savedTShirt = tShirtRepository.save(tShirt);

        // Then
        assertThat(savedTShirt.getId()).isNotNull();
        assertThat(savedTShirt.getColor()).isEqualTo(tShirt.getColor());
        assertThat(savedTShirt.getSize()).isEqualTo(tShirt.getSize());
    }

    @Test
    public void shouldFindTShirtById() {
        // Given
        Tshirt tShirt = new Tshirt();
        tShirt.setColor("red");
        tShirt.setSize("M");
        Tshirt savedTShirt = tShirtRepository.save(tShirt);

        // When
        Tshirt foundTShirt = tShirtRepository.findById(savedTShirt.getId()).orElse(null);

        // Then
        assertThat(foundTShirt).isNotNull();
        assertThat(foundTShirt.getId()).isEqualTo(savedTShirt.getId());
        assertThat(foundTShirt.getColor()).isEqualTo(savedTShirt.getColor());
        assertThat(foundTShirt.getSize()).isEqualTo(savedTShirt.getSize());
    }

    @Test
    public void shouldFindAllTShirts() {
        // Given
        Tshirt tShirt1 = new Tshirt();
        tShirt1.setColor("green");
        tShirt1.setSize("S");
        tShirtRepository.save(tShirt1);

        Tshirt tShirt2 = new Tshirt();
        tShirt2.setColor("blue");
        tShirt2.setSize("M");
        tShirtRepository.save(tShirt2);

        Tshirt tShirt3 = new Tshirt();
        tShirt3.setColor("red");
        tShirt3.setSize("L");
        tShirtRepository.save(tShirt3);

        // When
        List<Tshirt> tShirts = tShirtRepository.findAll();

        // Then
        assertThat(tShirts).hasSize(3);
        assertThat(tShirts).contains(tShirt1, tShirt2, tShirt3);
    }

    @Test
    public void shouldUpdateTShirt() {
        // Given
        Tshirt tShirt = new Tshirt();
        tShirt.setColor("black");
        tShirt.setSize("S");
        Tshirt savedTShirt = tShirtRepository.save(tShirt);

        // When
        savedTShirt.setColor("white");
        Tshirt updatedTShirt = tShirtRepository.save(savedTShirt);

        // Then
        assertThat(updatedTShirt.getId()).isEqualTo(savedTShirt.getId());
        assertThat(updatedTShirt.getColor()).isEqualTo("white");
        assertThat(updatedTShirt.getSize()).isEqualTo(savedTShirt.getSize());
    }

    @Test
    public void shouldReturnTShirtsBySize() {
        Tshirt tShirt1 = new Tshirt();
        tShirt1.setColor("red");
        tShirt1.setSize("M");
        tShirtRepository.save(tShirt1);

        Tshirt tShirt2 = new Tshirt();
        tShirt2.setColor("blue");
        tShirt2.setSize("L");
        tShirtRepository.save(tShirt2);

        List<Tshirt> tShirtsBySize = tShirtRepository.findBySize("M");

        assertThat(tShirtsBySize.size()).isEqualTo(1);
        assertThat(tShirtsBySize.get(0).getColor()).isEqualTo("red");
    }

    @Test
    public void testDummy() {
        assertTrue(true);
    }
}
