package su.productsshop.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.productsshop.entiies.product.ExportProductXMLDTO;
import su.productsshop.entiies.product.Product;
import su.productsshop.entiies.user.ExportUserWithSoldProductsXMLDTO;
import su.productsshop.entiies.user.ExportUsersWithSoldProductsXMLDTO;
import su.productsshop.entiies.user.User;
import su.productsshop.repository.UserRepository;
import su.productsshop.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ExportUsersWithSoldProductsXMLDTO findAllWithSoldProducts() {
        List<User> users = userRepository.findAllWithSoldProducts();


        List<ExportUserWithSoldProductsXMLDTO> userDTOs =
                users.stream()
                        .map(u->this.modelMapper.map(u,ExportUserWithSoldProductsXMLDTO.class ))
                        .collect(Collectors.toList());

        return  new ExportUsersWithSoldProductsXMLDTO(userDTOs);
    }
}
