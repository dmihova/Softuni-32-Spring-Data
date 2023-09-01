package su.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import su.gamestore.model.game.Game;
import su.gamestore.model.game.GameDetails;
import su.gamestore.model.game.GameNameDTO;
import su.gamestore.model.game.GameNamePriceDTO;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    int countByTitle(String title);
    int countByTitleAndDeletedFalse(String title);
    int countByTitleIsDeleted(String title, boolean b);

    Optional<Game> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.deleted = true WHERE g.id = :id AND g.deleted = false")
    int updateDeleteTrueById(Long id);

    @Query("SELECT g.title AS title FROM Game g WHERE g.id = :id")
    GameNameDTO selectTitleById(Long id);

    @Query("SELECT g.title AS title, g.price AS price FROM Game g")
    Set<GameNamePriceDTO> selectAllTitleAndPrice();

    Optional<GameDetails> findByTitle(String title);

    Set<Game> findByTitleIn(Collection<String> cart);
}
