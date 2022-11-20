package su.advquerying.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.advquerying.entities.Shampoo;
import su.advquerying.entities.Size;
import su.advquerying.repositories.ShampooRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService{
    @Autowired
    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findByBrand(String brand) {
      return   shampooRepository.findByBrand(brand);
    }

    @Override
    public List<Shampoo> findByBrandAndSize(String brand, Size size) {
        return   shampooRepository.findByBrandAndSize(brand,size);
    }

    @Override
    public List<Shampoo> findBySize(Size size) {
        return   shampooRepository.findBySize(size);
    }

    @Override
    public List<Shampoo> findBySizeOrderByIdAsc(Size size) {
        return   shampooRepository.findBySizeOrderByIdAsc(size);
    }

    @Override
    public List<Shampoo> findByIngredientName(String ingredient) {
        return   shampooRepository.findByIngredientName(ingredient);
    }

    @Override
    public List<Shampoo> findByIngredientNameIn(List<String> names) {
        return  shampooRepository.findByIngredientNameIn(names);
    }

    @Override
    public List<Shampoo> findByIngredientsIn(List<String> ingredients) {
        return   shampooRepository.findByIngredientsIn( ingredients);
    }

    @Override
    public List<Shampoo> findBySizeOrLabelId(Size size, Long labelId) {
        return shampooRepository.findBySizeOrLabelId( size,labelId);
    }

    @Override
    public List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return    shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public Long countPriceLessThan(BigDecimal price) {
        return shampooRepository.countByPriceLessThan(price);
    }


    @Override
    public List<Shampoo> findByIngredientCountLessThan(int count) {
        return shampooRepository.findByIngredientCountLessThan(count);
    }



}
