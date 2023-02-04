package su.productsshop.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import su.productsshop.entity.category.CategoryStatsDTO;
import su.productsshop.entity.product.ProductWithoutBuyerDTO;
import su.productsshop.entity.product.ProductWithoutBuyerDTOI;
import su.productsshop.repository.CategoryRepository;
import su.productsshop.repository.ProductRepository;
import su.productsshop.repository.UserRepository;
import su.productsshop.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final UserRepository userRepository;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    public ProductServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper mapper, ModelMapper mapper1, Gson gson) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

        this.mapper = mapper1;
        this.gson = gson;
    }

    @Override
    public List<ProductWithoutBuyerDTOI> getProductsInPriceRangeForSellI(float min, float max) {
        BigDecimal rangeStart =  BigDecimal.valueOf(min);
        BigDecimal rangeEnd =    BigDecimal.valueOf(max);

        return this.productRepository
                .findByBuyerNullAndPriceBetweenOrderByPrice(rangeStart, rangeEnd);
    }

    @Override
    public List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(float min, float max) {
        BigDecimal rangeStart =  BigDecimal.valueOf(min);
        BigDecimal rangeEnd =    BigDecimal.valueOf(max);

        return this.productRepository
                .findByBuyerNullAndPriceBetweenOrderByPriceqQ(rangeStart, rangeEnd);
    }

    @Override
    public List<CategoryStatsDTO> getCategoryStatistics() {
        return this.productRepository.getCategoryStatistics();
    }
}
