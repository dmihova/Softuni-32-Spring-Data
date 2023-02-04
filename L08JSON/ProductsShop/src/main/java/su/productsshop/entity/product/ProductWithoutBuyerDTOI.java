package su.productsshop.entity.product;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface ProductWithoutBuyerDTOI {
      String getName();
      BigDecimal getPrice();

      @Value("#{target.seller.firstName+ ' ' +target.seller.lastName}")
      String getSeller();


}
