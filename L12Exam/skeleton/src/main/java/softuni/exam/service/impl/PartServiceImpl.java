package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.config.ImportFiles;
import softuni.exam.models.dto.PartImportJSONDTO;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;
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
public class PartServiceImpl implements PartService {
    @Autowired
    private final PartRepository partRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper mapper;


    public PartServiceImpl(PartRepository partRepository, Gson gson, Validator validator, ModelMapper mapper) {
        this.partRepository = partRepository;
        this.gson = gson;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {

        return this.partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(ImportFiles.PARTS_FILE);
    }

    @Override
    public String importParts() throws IOException {
        final String json = this.readPartsFileContent();

        final PartImportJSONDTO[] importParts = this.gson.fromJson(json, PartImportJSONDTO[].class);

        final List<String> outputMessage = new ArrayList<>();

        for (PartImportJSONDTO importPart : importParts) {

            final Set<ConstraintViolation<PartImportJSONDTO>> validationErrors = this.validator.validate(importPart);

            if (validationErrors.isEmpty()) {

                final Optional<Part> PartOptional = this.partRepository.findByPartName(importPart.getPartName());

                if (PartOptional.isEmpty()) {

                    Part part = this.mapper.map(importPart, Part.class);

                    this.partRepository.save(part);

                    outputMessage.add(String.format(Messages.SUCCESSFULLY_IMPORTED_PART, part.getPartName(), part.getPrice()));

                } else {
                    outputMessage.add(Messages.INVALID_PART);
                }

            } else {
                outputMessage.add(Messages.INVALID_PART);
            }
        }
        return String.join(System.lineSeparator(), outputMessage);
    }

}
