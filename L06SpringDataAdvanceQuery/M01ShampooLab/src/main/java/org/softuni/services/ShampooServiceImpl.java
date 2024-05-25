package org.softuni.services;

import org.softuni.entities.Shampoo;
import org.softuni.entities.Size;
import org.softuni.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ShampooServiceImpl implements ShampooService {
    @Autowired
    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }


    @Override
    public List<Shampoo> findByBrand(String brand) {
        return shampooRepository.findByBrand(brand);
    }

    @Override
    public List<Shampoo> findBySize(Size size) {
        return shampooRepository.findBySize(size);
    }

    @Override
    public List<Shampoo> findByBrandAndSize(String brand, Size size) {
        return shampooRepository.findByBrandAndSize(brand, size);
    }

    @Override
    public List<Shampoo> findBySizeOrderByIdAsc(Size size) {
        return shampooRepository.findBySizeOrderByIdAsc(size);
    }

    @Override
    public List<Shampoo> findByIngredientsNameIn(Collection<String> names) {
        return shampooRepository.findByIngredients_NameIn(names);
    }

    @Override
    public List<Shampoo> findByIngredientName(String name) {
        return shampooRepository.findByIngredients_Name(name);
    }

    @Override
    public List<Shampoo> findBySizeOrLabel_TitleOrderByPriceDesc(Size size, String label) {
        return shampooRepository.findBySizeOrLabel_TitleOrderByPriceDesc(size, label);
    }

    @Override
    public List<Shampoo> findByIngredientNameQry(String name) {
        return shampooRepository.findByIngredientNameQry(name);
    }

    @Override
    public List<Shampoo> findByIngredientNameInQry(List<String> ingredients) {
        return shampooRepository.findByIngredientNameInQry(ingredients);
    }

    @Override
    public List<Shampoo> findBySizeOrLabel_IdOrderByPriceDesc(Size size, Long labelId) {
        return shampooRepository.findBySizeOrLabel_IdOrderByPriceDesc(size, labelId);
    }

    @Override
    public List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public Long countByPriceLessThan(BigDecimal price) {
        return shampooRepository.countByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> findByIngredientsCount(int count) {
        return shampooRepository.findByIngredientsCount(count);
    }

    @Override
    public List<Shampoo> findByIngredientsCountLess(int count) {
        return shampooRepository.findByIngredientsCountLess(count);
    }

    @Override
    public List<Shampoo> findFirstOrderByIngredientsSizeDesc() {
        return shampooRepository.findFirstOrderByIngredientsSizeDesc();
    }


}
