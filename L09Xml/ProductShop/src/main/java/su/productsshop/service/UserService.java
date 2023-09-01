package su.productsshop.service;

import su.productsshop.entiies.user.ExportUsersWithSoldProductsXMLDTO;

public interface UserService {

    ExportUsersWithSoldProductsXMLDTO findAllWithSoldProducts();
}
