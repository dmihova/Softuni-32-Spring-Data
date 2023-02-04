package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.config.ImportFiles;
import softuni.exam.models.dto.TaskImportXMLDTO;
import softuni.exam.models.dto.TasksWrapperXMLDTO;
import softuni.exam.models.entity.*;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.repository.PartRepository;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.TaskService;
import softuni.exam.util.Messages;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final CarRepository carRepository;
    private final MechanicRepository mechanicRepository;
    private final PartRepository partRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper mapper;

    public TaskServiceImpl(TaskRepository taaskRepository, CarRepository carRepository,
                           MechanicRepository mechanicRepository, PartRepository partRepository,
                           Gson gson, Validator validator, ModelMapper mapper) {
        this.taskRepository = taaskRepository;
        this.carRepository = carRepository;
        this.mechanicRepository = mechanicRepository;
        this.partRepository = partRepository;
        this.gson = gson;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(ImportFiles.TASKS_FILE);
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        final FileReader fileReader = new FileReader(ImportFiles.TASKS_FILE.toFile());

        final JAXBContext context = JAXBContext.newInstance(TasksWrapperXMLDTO.class);
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final TasksWrapperXMLDTO carsDto = (TasksWrapperXMLDTO) unmarshaller.unmarshal(fileReader);

        return carsDto
                .getTasks()
                .stream()
                .map(this::importTask)
                .collect(Collectors.joining(System.lineSeparator()));

    }

    private String importTask(TaskImportXMLDTO dto) {

        Set<ConstraintViolation<TaskImportXMLDTO>> validationMessages = this.validator.validate(dto);

        if (!validationMessages.isEmpty()) {
            return Messages.INVALID_TASK;
        }


        Optional<Car> carOptional = this.carRepository.findById(dto.getCar().getId());
        if (!carOptional.isPresent()) {
            return Messages.INVALID_TASK;
        }
        Optional<Part> partOptional = this.partRepository.findById(dto.getPart().getId());
        if (!partOptional.isPresent()) {
            return Messages.INVALID_TASK;
        }

        Optional<Mechanic> mechanicOptional = this.mechanicRepository.findByFirstName(dto.getMechanic().getFirstName());
        if (!mechanicOptional.isPresent()) {
            return Messages.INVALID_TASK;
        }
        Task task = this.mapper.map(dto, Task.class);
        task.setCar(carOptional.get());
        task.setMechanic(mechanicOptional.get());
        task.setPart(partOptional.get());
        this.taskRepository.save(task);

        return String.format(Messages.SUCCESSFULLY_IMPORTED_TASK, task.getPrice());
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        return this.taskRepository
                .findAllByCar_CarTypeOrderByPriceDesc(CarType.coupe)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(this::infoExpensiveTask)
                .collect(Collectors.joining(System.lineSeparator()));


    }

    private String infoExpensiveTask(Task task) {
        return String.format(Messages.INFO_EXPENSIVE_TASK,
                task.getCar().getCarMake(), task.getCar().getCarModel(), task.getCar().getKilometers(),
                task.getMechanic().getFirstName(), task.getMechanic().getLastName(), task.getId(),
                task.getCar().getEngine(),
                task.getPrice()
        );
    }

}

