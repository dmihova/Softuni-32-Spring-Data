package com.example.football.service.impl;

import com.example.football.models.dto.PlayerImportDto;
import com.example.football.models.dto.PlayerWrapperDto;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.Messages;
import com.example.football.util.PathFiles;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final StatRepository statRepository;

    private final Gson gson;
    private final Validator validator;
    private final ModelMapper mapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository,
                             TownRepository townRepository, StatRepository statRepository, Gson gson, Validator validator, ModelMapper mapper) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;
        this.gson = gson;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(PathFiles.PLAYERS_PATH);
    }

    @Override
    public String importPlayers() throws FileNotFoundException, JAXBException {
        final FileReader fileReader = new FileReader(PathFiles.PLAYERS_PATH.toFile());

        final JAXBContext context = JAXBContext.newInstance(PlayerWrapperDto.class);
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final PlayerWrapperDto playersDto = (PlayerWrapperDto) unmarshaller.unmarshal(fileReader);


        return playersDto
                .getPlayers()
                .stream()
                .map(this::importPlayer)
                .collect(Collectors.joining(System.lineSeparator()));
    }


    private String importPlayer(PlayerImportDto dto) {

        Set<ConstraintViolation<PlayerImportDto>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return Messages.INVALID + Messages.PLAYER;
        }

        Optional<Player> playerExist = this.playerRepository.findByEmail(dto.getEmail());

        if (playerExist.isPresent()) {
            return Messages.INVALID + Messages.PLAYER;
        }

        Player player = this.mapper.map(dto, Player.class);

        Town town =
                this.townRepository
                        .findByName(dto.getTown().getName())
                        .orElseThrow(NoSuchElementException::new);

        Team team =
                this.teamRepository
                        .findByName(dto.getTeam().getName())
                        .orElseThrow(NoSuchElementException::new);

     Stat stat =
                this.statRepository
                        .findById(dto.getStat().getId())
                        .orElseThrow(NoSuchElementException::new);


        player.setTown(town);
        player.setTeam(team);
        player.setStat(stat);
        this.playerRepository.save(player);

        return Messages.SUCCESSFULLY + Messages.PLAYER + Messages.INTERVAL + player.importInfo();
    }

    @Override
    public String exportBestPlayers() {
          DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
          LocalDate from = LocalDate.parse("01-01-1995", dateFormat);
          LocalDate to = LocalDate.parse("01-01-2003", dateFormat);

        return this.playerRepository
                .findByBirthDateBetweenOrderByStat_ShootingDescStat_PassingDescStat_EnduranceDescLastNameAsc(from, to)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(Player::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
