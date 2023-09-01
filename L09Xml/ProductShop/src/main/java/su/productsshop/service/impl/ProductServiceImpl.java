package su.productsshop.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.productsshop.entiies.product.Product;
import su.productsshop.entiies.product.ExportProductXMLDTO;
import su.productsshop.entiies.product.ExportProductsInRangeXMLDTO;
import su.productsshop.entiies.user.User;
import su.productsshop.repository.ProductRepository;
import su.productsshop.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final  ModelMapper modelMapper;
    private final  ModelMapper productToDTOMapper;
    private final TypeMap<Product, ExportProductXMLDTO> productToDTOMapping;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
         Converter<User,String> userToFullName= context
                      -> context.getSource()==null?null: context.getSource().getFullName();
         this.productToDTOMapper=new ModelMapper();

        TypeMap<Product, ExportProductXMLDTO> typeMap =
                    this.productToDTOMapper.createTypeMap(Product.class, ExportProductXMLDTO.class);

        this.productToDTOMapping = typeMap.addMappings(map->
                        map.using(userToFullName)
                        .map(Product::getSeller, ExportProductXMLDTO::setSeller));


    }

    @Override
    @Transactional
    public ExportProductsInRangeXMLDTO getInPriceRangeForSell(double from, double to) {
        BigDecimal fromRange  = BigDecimal.valueOf(from);
        BigDecimal toRange  = BigDecimal.valueOf(to);
        List<Product> products =
                productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(fromRange,toRange);

        List <ExportProductXMLDTO> exportProductXMLDTOS =
                    products.stream()
                            .map(p->this.productToDTOMapping.map(p ))
                            .collect(Collectors.toList());

        return new ExportProductsInRangeXMLDTO(exportProductXMLDTOS);
    }
}
