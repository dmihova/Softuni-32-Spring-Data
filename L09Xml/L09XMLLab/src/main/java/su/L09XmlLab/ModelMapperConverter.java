package su.L09XmlLab;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.L09XmlLab.model.dto.BirthdayDTO;
import su.L09XmlLab.model.entity.Birthday;

import java.time.LocalDate;

@Component
public class ModelMapperConverter implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
//        Converter<String, LocalDate> localDateConverter = new Converter<String, LocalDate>() {
//            @Override
//            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
//                return LocalDate.parse(mappingContext.getSource());
//            }
//        };

        Converter<String, LocalDate> localDateConverter = mappingContext -> LocalDate.parse(mappingContext.getSource());
        modelMapper.addConverter(localDateConverter,String.class,LocalDate.class);


        Birthday birthday = new Birthday();
        birthday.setDate(LocalDate.now());

        BirthdayDTO mapDTO = modelMapper.map(birthday, BirthdayDTO.class);
        System.out.println(mapDTO.getDate());


        Birthday birthday2 = modelMapper.map(mapDTO, Birthday.class);
        System.out.println(birthday2.getDate());


    }
}
