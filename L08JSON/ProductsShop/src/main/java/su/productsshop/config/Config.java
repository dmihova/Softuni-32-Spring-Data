package su.productsshop.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.LocalDate;

import java.util.Scanner;

@Configuration
public class Config {
    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
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
