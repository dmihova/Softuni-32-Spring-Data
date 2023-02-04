package su.productsshop.service;

import su.productsshop.entity.user.UserWithSoldProductsDTO;
import su.productsshop.entity.user.UsersCountWrapperDTO;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsDTO> getUserWithSoldProducts();

    UsersCountWrapperDTO findUsersSoldProductsWithCount();
}
