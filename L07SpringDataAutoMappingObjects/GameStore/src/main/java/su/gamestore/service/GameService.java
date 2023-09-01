package su.gamestore.service;

import su.gamestore.model.game.GameAddUpdateDTO;
import su.gamestore.model.game.Game;

import java.util.Optional;

public interface GameService {
    String addGame(GameAddUpdateDTO gameAddUpdateDTO);

    Optional<Game> getByIdAndNotDeleted(Long id);

    String editGame(String[] input);

    String deleteGame(long id);

    String allGames();

    String detailsGame(String title);
}
