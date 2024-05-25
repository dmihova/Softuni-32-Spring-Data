package org.softuni.repositories;

import org.softuni.models.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
