package su.gamestore.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.gamestore.constants.OutputMessages;
import su.gamestore.model.game.GameAddUpdateDTO;
import su.gamestore.model.game.Game;
import su.gamestore.model.game.GameDetails;
import su.gamestore.model.game.GameNamePriceDTO;
import su.gamestore.repository.GameRepository;
import su.gamestore.service.GameService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public String addGame(GameAddUpdateDTO gameAddUpdateDTO) {
        List<String> invalidDataMessage = gameAddUpdateDTO.getValidationMessages();
        if (!invalidDataMessage.isEmpty()) {
            return invalidDataMessage
                    .stream().collect(Collectors.joining(System.lineSeparator()));
        }
        int countByName = this.gameRepository.countByTitleAndDeletedFalse(gameAddUpdateDTO.getTitle() );
        if (countByName>0){
            return OutputMessages.GAME_EXIST;
        }

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<GameAddUpdateDTO, Game>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        Game game = mapper.map(gameAddUpdateDTO, Game.class);

        gameRepository.save(game);
        return String.format(OutputMessages.GAME_SUCCESSFULLY_ADDED, game.getTitle());
    }

    @Override
    public String editGame(String[] input) {

        Long id = Long.parseLong(input[1]);

        Optional<Game> gameToEdit = gameRepository.findById(id);

        if (gameToEdit.isEmpty()) {
           return String.format(OutputMessages.GAME_WRONG_ID, id);
        }

        Game game = gameToEdit.get();
        String oldTitle =game.getTitle();

        GameAddUpdateDTO updateGameDTO = new GameAddUpdateDTO(input);
        List<String> invalidDataMessage = updateGameDTO.getValidationMessages();
        if (!invalidDataMessage.isEmpty()) {
            return invalidDataMessage
                    .stream().collect(Collectors.joining(System.lineSeparator()));
        }


        game.update(updateGameDTO);

        if (!oldTitle.equals(game.getTitle())){
            if(gameRepository.countByTitleAndDeletedFalse(game.getTitle() ) > 0){
                return OutputMessages.GAME_EXIST;
            }
        }

        gameRepository.save(game);
        return  String.format(OutputMessages.GAME_UPDATED, id);
    }

    @Override
    public String deleteGame(long id) {
        Optional<Game> gameOptional=  getByIdAndNotDeleted(  id);
        if(gameOptional.isEmpty()){
            return OutputMessages.GAME_WRONG_ID;
        }
        Game game =gameOptional.get();
        game.setDeleted(true);
        gameRepository.save(game);
        return String.format(OutputMessages.GAME_DELETED);
    }


    @Override
    public Optional<Game> getByIdAndNotDeleted(Long id) {
        return gameRepository.findByIdAndDeletedFalse(id);
    }


    @Override
    public String allGames() {
        return gameRepository.selectAllTitleAndPrice()
                .stream()
                .map(GameNamePriceDTO::displayInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String detailsGame(String title) {
        Optional<GameDetails> gameDetails = gameRepository.findByTitle(title);
        if(gameDetails.isEmpty()){
            String.format(OutputMessages.GAME_WITH_TITLE_NOT_EXIST);
        }
        return gameDetails.get().displayInfo();
    }
}
