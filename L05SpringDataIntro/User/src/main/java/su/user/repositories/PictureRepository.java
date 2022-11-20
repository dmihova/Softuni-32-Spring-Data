package su.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import su.user.models.Picture;

public interface PictureRepository extends JpaRepository<Picture, Integer>  {
}
