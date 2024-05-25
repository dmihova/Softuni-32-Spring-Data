package org.softuni.repositories;

import org.softuni.models.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PictureRepository extends JpaRepository<Picture, Integer>  {
}
