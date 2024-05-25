package org.softuni;

import org.softuni.entities.Ingredient;
import org.softuni.entities.Shampoo;
import org.softuni.services.IngredientService;
import org.softuni.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.softuni.entities.Size.MEDIUM;
import static org.softuni.entities.Size.SMALL;

@Component
public class Main implements CommandLineRunner {


    private ShampooService shampooService;
    private IngredientService ingredientService;

    @Autowired
    public Main(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }


    @Override
    public void run(String... args) throws Exception {
        //e1FindByBrand("Swiss Green Apple & Nettle");
        //e1FindByBrand("Volume & Fullness Lavender");
        //e1FindBySize();
        //e1FindByIngredients();
        //e2FindShampooBySizeOrLabel();
        //e3FindShampooByPrice();
        // e4FindIngredientNameStart();
        // e5FindIngredientByNames();
        //e6CountShampooByPrice();
        //e7findShampooByIngredientNames();
        // e8ShampooByIngredientsCount();
       // e8ShampooByMaxIngredients();//

        // e9DeleteIngredientByName();
         e10UpdatePrice();
         e10UpdatePriceForName();
    }



    private void e1FindByBrand(String brand) {
        for (Shampoo shampoo : this.shampooService.findByBrand(brand)) {
            System.out.println(shampoo.getLabel().getTitle());
        }
    }

    private void e1FindBySize() {
        for (Shampoo shampoo : this.shampooService.findByBrandAndSize("Cotton Fresh", SMALL)) {
            System.out.println(shampoo);
        }

        for (Shampoo shampoo : this.shampooService.findBySize(MEDIUM)) {
            System.out.println(shampoo);
        }
        for (Shampoo shampoo : this.shampooService.findBySizeOrderByIdAsc(MEDIUM)) {
            System.out.println(shampoo);
        }
    }

    private void e1FindByIngredients() {
        for (Shampoo shampoo : this.shampooService.findByIngredientName("Apple")) {
            System.out.println(shampoo);
        }

        for (Shampoo shampoo : this.shampooService.findByIngredientNameQry("Apple")) {
            System.out.println(shampoo);
        }

        List<String> ingredients = new ArrayList<>();
        ingredients.add("Apple");
        ingredients.add("Berry");
        for (Shampoo shampoo : this.shampooService.findByIngredientsNameIn(ingredients)) {
            System.out.println(shampoo);
        }
        for (Shampoo shampoo : this.shampooService.findByIngredientNameInQry(ingredients)) {
            System.out.println(shampoo);
        }
    }

    private void e2FindShampooBySizeOrLabel() {
        Long labelId = 3L;
        for (Shampoo shampoo : this.shampooService.findBySizeOrLabel_IdOrderByPriceDesc(MEDIUM, labelId)) {
            System.out.println(shampoo);
        }
        for (Shampoo shampoo : this.shampooService.findBySizeOrLabel_TitleOrderByPriceDesc(MEDIUM, "Flat & thin hair.")) {
            System.out.println(shampoo.getLabel().getTitle());
        }
    }

    private void e3FindShampooByPrice() {
        BigDecimal price = BigDecimal.valueOf(12.0);
        for (Shampoo shampoo : this.shampooService.findByPriceGreaterThanOrderByPriceDesc(price)) {
            System.out.println(shampoo);
        }
    }

    private void e4FindIngredientNameStart() {
        String name = "M";
        for (Ingredient ingredient : this.ingredientService.findByNameStartsWith(name)) {
            System.out.println(ingredient);
        }
    }

    private void e5FindIngredientByNames() {
        List<String> ingredientNames = new ArrayList<>();
        ingredientNames.add("Lavender");
        ingredientNames.add("Herbs");
        ingredientNames.add("Apple");
        ingredientNames.add("Berry");
        for (Ingredient ingredient : this.ingredientService.findByNameInOrderByPriceDesc(ingredientNames)) {
            System.out.println(ingredient);
        }

    }
    private void e6CountShampooByPrice() {
        BigDecimal price = BigDecimal.valueOf(8.50);
        Long count = this.shampooService.countByPriceLessThan(price);
        System.out.println(count);
    }

    private void e7findShampooByIngredientNames() {
        List<String> ingredients = new ArrayList();
        ingredients.add("Lavender");
        ingredients.add("Herbs");
        ingredients.add("Apple");
        ingredients.add("Berry");


        for (Shampoo shampoo : this.shampooService.findByIngredientNameInQry(ingredients)) {
            System.out.println(shampoo);
        }
    }



    private void e8ShampooByIngredientsCount() {
        for (Shampoo shampoo : this.shampooService.findByIngredientsCount(3)) {
            System.out.println(shampoo);
        }
        for (Shampoo shampoo : this.shampooService.findByIngredientsCountLess(3)) {
            System.out.println(shampoo);
        }
    }

    private void e8ShampooByMaxIngredients() {

         for (Shampoo shampoo : this.shampooService.findFirstOrderByIngredientsSizeDesc()) {
            System.out.println(shampoo);
        }

    }
    private void e9DeleteIngredientByName() {
        String ingredient = "Lavender";
        this.ingredientService.deleteByName(ingredient);
    }

    private void e10UpdatePrice() {
        this.ingredientService.updateAllPrice();
    }


  private void e10UpdatePriceForName() {
        List<String> ingredients = new ArrayList();
        ingredients.add("Lavender");
        ingredients.add("Herbs");
        ingredients.add("Apple");
        ingredients.add("Berry");
        BigDecimal increase = BigDecimal.valueOf(0.1);
        ingredientService.updatePriceWhereNameIn(increase,ingredients);
    }


}
