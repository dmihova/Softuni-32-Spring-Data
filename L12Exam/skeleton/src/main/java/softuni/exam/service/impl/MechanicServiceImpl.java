package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.config.ImportFiles;
import softuni.exam.models.dto.MechanicImportJSONDTO;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;
import softuni.exam.util.Messages;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MechanicServiceImpl implements MechanicService {
    private final MechanicRepository mechanicRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper mapper;

    public MechanicServiceImpl(MechanicRepository mechanicRepository, Gson gson, Validator validator, ModelMapper mapper) {
        this.mechanicRepository = mechanicRepository;
        this.gson = gson;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(ImportFiles.MECHANICS_FILE);
    }

    @Override
    public String importMechanics() throws IOException {
        final String json = this.readMechanicsFromFile();

        final MechanicImportJSONDTO[] importMechanicsDTOS = this.gson.fromJson(json, MechanicImportJSONDTO[].class);

        final List<String> outputMessage = new ArrayList<>();

        for (MechanicImportJSONDTO importMechanicDTO : importMechanicsDTOS) {
            String message = importMechanic(importMechanicDTO);
            outputMessage.add(message);

        }
        return String.join(System.lineSeparator(), outputMessage);
    }


    private String importMechanic(MechanicImportJSONDTO importMechanicDTO) {

        final Set<ConstraintViolation<MechanicImportJSONDTO>> validationErrors =
                this.validator.validate(importMechanicDTO);

        if (!validationErrors.isEmpty()) {
            return Messages.INVALID_MECHANIC;
        }
        final Optional<Mechanic> MechanicOptionalNameEmail =
                this.mechanicRepository.findByFirstNameOrEmail
                        (importMechanicDTO.getFirstName(), importMechanicDTO.getEmail());
        if (!MechanicOptionalNameEmail.isEmpty()) {
            return Messages.INVALID_MECHANIC;
        }

        if (importMechanicDTO.getPhone() != null) {
            final Optional<Mechanic> MechanicOptionalPhone =
                    this.mechanicRepository.findByPhone(importMechanicDTO.getPhone());
            if (!MechanicOptionalPhone.isEmpty()) {
                return Messages.INVALID_MECHANIC;
            } else {
                return createMechanic(importMechanicDTO);
            }
        } else {
            return createMechanic(importMechanicDTO);
        }
    }

    private String createMechanic(MechanicImportJSONDTO importMechanicDTO) {
        Mechanic mechanic = this.mapper.map(importMechanicDTO, Mechanic.class);
        this.mechanicRepository.save(mechanic);
        return String.format(Messages.SUCCESSFULLY_IMPORTED_MECHANIC, mechanic.getFullName());
    }

}

