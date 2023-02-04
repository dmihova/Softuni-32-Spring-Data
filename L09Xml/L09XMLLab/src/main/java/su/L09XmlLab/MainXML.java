package su.L09XmlLab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.L09XmlLab.model.dto.addresses.AddressDtlXMLDTO;
import su.L09XmlLab.model.dto.addresses.AddressXMLDTO;
import su.L09XmlLab.model.dto.addresses.CountryXMLDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;


//@Component
public class MainXML implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

     //   addressToXml();
      addressFromXml();

    }

    private void addressFromXml() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(AddressXMLDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream inputStream = getClass().getResourceAsStream("/files/users.xml");
        BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream));

        AddressXMLDTO addressXMLDTO =(AddressXMLDTO) unmarshaller.unmarshal(bfr);
        System.out.println(addressXMLDTO.toString());



    }

    private static void addressToXml() throws JAXBException {
        CountryXMLDTO countryXMLDTO =new CountryXMLDTO("Bulgaria class");
        // AddressXMLDTO addressXMLDTO = new AddressXMLDTO(6, "Bulgaria", "Sofia");
        //AddressXMLDTO addressXMLDTO = new AddressXMLDTO(6, "Bulgaria", countryXMLDTO,"Sofia");
        AddressDtlXMLDTO ad1 = new AddressDtlXMLDTO("line1");
        AddressDtlXMLDTO ad2 = new AddressDtlXMLDTO("line2");
        AddressDtlXMLDTO ad3 = new AddressDtlXMLDTO("line3");
        AddressXMLDTO addressXMLDTO =new AddressXMLDTO(6, "Sofia","Bulgaria", countryXMLDTO,  List.of(ad1,ad2,ad3));

        JAXBContext jaxbContext = JAXBContext.newInstance(addressXMLDTO.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(addressXMLDTO, System.out);
        marshaller.marshal(addressXMLDTO, new File("users.xml"));
    }
}
