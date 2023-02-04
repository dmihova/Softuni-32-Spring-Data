package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.config.ImportFiles;
import softuni.exam.models.dto.CarImportXMLDTO;
import softuni.exam.models.dto.CarsWrapperXMLDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.Messages;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper mapper;

    public CarServiceImpl(CarRepository carRepository, Gson gson, Validator validator, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(ImportFiles.CARS_FILE);
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        final FileReader fileReader = new FileReader(ImportFiles.CARS_FILE.toFile());

        final JAXBContext context = JAXBContext.newInstance(CarsWrapperXMLDTO.class);
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final CarsWrapperXMLDTO carsDto = (CarsWrapperXMLDTO) unmarshaller.unmarshal(fileReader);

        return carsDto
                .getCars()
                .stream()
                .map(this::importCar)
                .collect(Collectors.joining(System.lineSeparator()));

    }

    private String importCar(CarImportXMLDTO dto) {

        Set<ConstraintViolation<CarImportXMLDTO>> validationMessages = this.validator.validate(dto);

        if (!validationMessages.isEmpty()) {
            return Messages.INVALID_CAR;
        }

        Optional<Car> carOptional = this.carRepository.findByPlateNumber(dto.getPlateNumber());

        if (carOptional.isPresent()) {
            return Messages.INVALID_CAR;
        }

        Car car = this.mapper.map(dto, Car.class);

        this.carRepository.save(car);

        return String.format(Messages.SUCCESSFULLY_IMPORTED_CAR, car.getCarMake(), car.getCarModel());
    }
}
