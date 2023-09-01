package su.productsshop.service;

import su.productsshop.entiies.product.ExportProductsInRangeXMLDTO;

public interface ProductService {

    ExportProductsInRangeXMLDTO getInPriceRangeForSell(double i, double i1);
}
