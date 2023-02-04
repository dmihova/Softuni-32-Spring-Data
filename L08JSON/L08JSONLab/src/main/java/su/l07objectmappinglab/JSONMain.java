package su.l07objectmappinglab;

import com.google.gson.*;
import org.springframework.boot.CommandLineRunner;
import su.l07objectmappinglab.model.dto.CompanyDTO;
import su.l07objectmappinglab.model.dto.addresses.CreateAddressDTO;
import su.l07objectmappinglab.model.dto.CreateEmployeeDTO;
import su.l07objectmappinglab.model.dto.DateDTO;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//@Component
public class JSONMain implements CommandLineRunner {
    public static class LocalDateAdapter implements JsonSerializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }
    @Override
    public void run(String... args) throws Exception {
        Gson gson = new GsonBuilder()
                 //.setPrettyPrinting()
                .setDateFormat("YYYY-MM-DD")
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        Scanner scanner = new Scanner(System.in);
        // toJson1(gson);
        // fromJsonEmlpoyee(gson, scanner);
        // listToJson(gson, scanner);
        // listFromJson(gson, scanner);
        // dates(gson, scanner);
       // companyToJson(gson, scanner);
        companyFromJson(gson,scanner);

    }

    private void companyFromJson(Gson gson, Scanner scanner) {
        //{"name":"Comp","employees":[{"firstName":"FName","lastName":"LNAme","addressDTO":{"country":"Bulgaria","city":"Varna"}},{"firstName":"FName2","lastName":"LNAme2","addressDTO":{"country":"Bulgaria","city":"Sofia"}},{"firstName":"FName3","lastName":"LNAme3","addressDTO":{"country":"Bulgaria","city":"Burgas"}}]}

        String input = scanner.nextLine();
        CompanyDTO companyDTO = gson.fromJson(input, CompanyDTO.class);

        System.out.println(companyDTO);
    }

    private void companyToJson(Gson gson, Scanner scanner) {
        CreateAddressDTO addressDTO = new CreateAddressDTO("Bulgaria", "Varna");
        CreateAddressDTO addressDTO2 = new CreateAddressDTO("Bulgaria", "Sofia");
        CreateAddressDTO addressDTO3 = new CreateAddressDTO("Bulgaria", "Burgas");
        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO("FName", "LNAme",
                BigDecimal.TEN, LocalDate.now(), addressDTO);
        CreateEmployeeDTO createEmployeeDTO2 = new CreateEmployeeDTO("FName2", "LNAme2",
                BigDecimal.TEN, LocalDate.now(), addressDTO2);
        CreateEmployeeDTO createEmployeeDTO3 = new CreateEmployeeDTO("FName3", "LNAme3",
                BigDecimal.TEN, LocalDate.now(), addressDTO3);

        CompanyDTO companyDTO = new CompanyDTO("Comp",
                List.of(createEmployeeDTO,createEmployeeDTO2,createEmployeeDTO3));

        System.out.println(gson.toJson(companyDTO));
    }


    private void dates(Gson gson, Scanner scanner) {
        DateDTO dto =new DateDTO(LocalDate.now());
        System.out.println(gson.toJson(dto));
    }


    private void listFromJson(Gson gson, Scanner scanner) {
        //[{"country":"Bulgaria","city":"Varna"},{"country":"Bulgaria","city":"Sofia"},{"country":"Bulgaria","city":"Burgas"}]
        String input = scanner.nextLine();
        List<Object> list = gson.fromJson(input, List.class);
        CreateAddressDTO[] arrayCreateAddressDTO = gson.fromJson(input, CreateAddressDTO[].class);

        System.out.println(
                Arrays.stream(arrayCreateAddressDTO).map(e -> e.toString()).collect(Collectors.joining(",")));
    }

    private void listToJson(Gson gson, Scanner scanner) {
        CreateAddressDTO addressDTO = new CreateAddressDTO("Bulgaria", "Varna");
        CreateAddressDTO addressDTO2 = new CreateAddressDTO("Bulgaria", "Sofia");
        CreateAddressDTO addressDTO3 = new CreateAddressDTO("Bulgaria", "Burgas");

        List<CreateAddressDTO> addressDTOs = List.of(addressDTO, addressDTO2, addressDTO3);

        System.out.println(gson.toJson(addressDTOs));
    }

    private static void fromJsonEmlpoyee(Gson gson, Scanner scanner) {
        //{"firstName":"FName","lastName":"LNAme","addressDTO":{"country":"Bulgaria","city":"Varna"}}
        String input = scanner.nextLine();
        CreateEmployeeDTO createEmployeeDTO = gson.fromJson(input, CreateEmployeeDTO.class);
        System.out.println(createEmployeeDTO);
    }

    private static void toJson1(Gson gson) {
        CreateAddressDTO addressDTO = new CreateAddressDTO("Bulgaria", "Varna");
        String resJson = gson.toJson(addressDTO);

        System.out.println(resJson);

        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO("FName", "LNAme",
                BigDecimal.TEN, LocalDate.now(), addressDTO);
        String resJsonEployee = gson.toJson(createEmployeeDTO);

        System.out.println(resJsonEployee);
    }


}
