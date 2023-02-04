package su.productsshop.service;

import su.productsshop.entity.category.CategoryStatsDTO;
import su.productsshop.entity.product.ProductWithoutBuyerDTO;
import su.productsshop.entity.product.ProductWithoutBuyerDTOI;

import java.util.List;

public interface ProductService {
    List<ProductWithoutBuyerDTOI> getProductsInPriceRangeForSellI(float min, float max);

    List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(float min, float max);

    List<CategoryStatsDTO> getCategoryStatistics();
}
