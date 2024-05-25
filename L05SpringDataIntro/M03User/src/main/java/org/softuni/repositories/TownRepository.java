package org.softuni.repositories;

import org.softuni.models.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {

    Town getByName(String name);
}
