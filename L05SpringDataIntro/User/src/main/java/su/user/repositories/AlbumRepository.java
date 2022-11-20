package su.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import su.user.models.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
