package su.productsshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import su.productsshop.entity.user.User;
import su.productsshop.entity.user.UserWithSoldProductsDTO;
import su.productsshop.entity.user.UsersCountWrapperDTO;
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
    public List<UserWithSoldProductsDTO> getUserWithSoldProducts() {
        List<User> all = this.userRepository.findAllWithSoldProducts();
        return all.stream()
                .map(user ->this.modelMapper.map(user,UserWithSoldProductsDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public UsersCountWrapperDTO findUsersSoldProductsWithCount() {
        List<User> all = this.userRepository.findAllWithSoldProductsOrderByCount();

//        List<UserDTO> userDTOs = all.stream()
//                .map(user -> modelMapper.map(user, UserDTO.class))
//                .toList();

     /*   userDTOs.
                forEach(u -> u.getSoldProducts()
                        .removeIf(p -> p.getBuyer() == null));
*/

/*        List<UserWrapperDetailsDto> usersWrapper =
                users
                        .stream()
                        .map(UserDto::userWrapperDetailsDto)
                        .toList();

        List<UserWrapperDetailsDto> sorted = usersWrapper
                .stream()
                .sorted(Comparator.comparing(UserWrapperDetailsDto::soldProductsCount)
                        .reversed()
                        .thenComparing(UserWrapperDetailsDto::getLastName))
                .toList();

        return new UsersCountWrapperDto(sorted);*/
        return null;
    }
}
