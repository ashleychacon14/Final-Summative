package com.company.gamestore.repository;
import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {
    List<Console> findAllByManufacturer(String manufacturer);

}