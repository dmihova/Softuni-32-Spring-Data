package su.L09XmlLab.config;

import com.google.gson.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 import java.time.LocalDate;
 import java.util.Scanner;

@Configuration
public class Config {

    @Bean
    public ModelMapper createModelMapper(){
        Converter<String, LocalDate> localDateConverter = mappingContext -> LocalDate.parse(mappingContext.getSource());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(localDateConverter,String.class,LocalDate.class);
        return modelMapper;
    }

    @Bean
    public Scanner createScanner(){return new Scanner(System.in);}


    @Bean
    public Gson createGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("YYYY-MM-DD")
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }





}
