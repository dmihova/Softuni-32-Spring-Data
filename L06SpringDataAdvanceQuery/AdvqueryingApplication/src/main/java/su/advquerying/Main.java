package su.advquerying;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.advquerying.entities.Ingredient;
import su.advquerying.entities.Shampoo;
import su.advquerying.entities.Size;
import su.advquerying.services.IngredientService;
import su.advquerying.services.ShampooService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        //e1FindByBrand();
        //e1FindBySize();
       // e1FindByIngredients();
        //e2FindShampooBySizeOrLabel();
        //e3FindShampooByPrice();
        //e4FindIngredientNameStart();
        // e5FindIngredientByNames();
        // e6CountShampooByPrice();
        // e7findShampooByIngredientNames();
        // e8ShampooByMaxIngredients();
        //e9DeleteIngredientByName();
        // e10UpdatePrice();
       // e10UpdatePriceForName();
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

    private void e10UpdatePrice() {
        this.ingredientService.updateAllPrice();
    }

    private void e9DeleteIngredientByName() {
        String ingredient = "Lavender";
        this.ingredientService.deleteByName(ingredient);
    }

    private void e7findShampooByIngredientNames() {
        List<String> ingredients = new ArrayList();
        ingredients.add("Lavender");
        ingredients.add("Herbs");
        ingredients.add("Apple");
        ingredients.add("Berry");


        for (Shampoo shampoo : this.shampooService.findByIngredientNameIn(ingredients)) {
            System.out.println(shampoo);
        }
    }

    private void e8ShampooByMaxIngredients() {
        int count=2;
        for (Shampoo shampoo : this.shampooService.findByIngredientCountLessThan(count)) {
            System.out.println(shampoo);
        }
    }

    private void e6CountShampooByPrice() {
        BigDecimal price = BigDecimal.valueOf(8.50);
        Long count = this.shampooService.countPriceLessThan(price);
        System.out.println(count);
    }

    private void e5FindIngredientByNames() {
        List<String> ingredients = new ArrayList();
        ingredients.add("Lavender");
        ingredients.add("Herbs");
        ingredients.add("Apple");
        ingredients.add("Berry");

//        for (Ingredient ingredient : this.ingredientService.findByNameIn(ingredients)) {
//            System.out.println(ingredient);
//        }
        for (Ingredient ingredient : this.ingredientService.findByNameInOrderByPrice(ingredients)) {
            System.out.println(ingredient);
        }

    }

    private void e4FindIngredientNameStart() {
        String name = "M";
        for (Ingredient ingredient : this.ingredientService.findByNameStartsWith(name)) {
            System.out.println(ingredient);
        }
    }

    private void e3FindShampooByPrice() {
        BigDecimal price = BigDecimal.valueOf(12.0);
        for (Shampoo shampoo : this.shampooService.findByPriceGreaterThanOrderByPriceDesc(price)) {
            System.out.println(shampoo);
        }
    }

    private void e2FindShampooBySizeOrLabel() {
        Size sizeM = Size.valueOf("MEDIUM");
        Long labelId = Long.valueOf(10);
        for (Shampoo shampoo : this.shampooService.findBySizeOrLabelId(sizeM, labelId)) {
            System.out.println(shampoo);
        }
    }

    private void e1FindByIngredients() {
        for (Shampoo shampoo : this.shampooService.findByIngredientName("Apple")) {
            System.out.println(shampoo);
        }

        List<String> ingredients = new ArrayList();
        ingredients.add("Apple");
        ingredients.add("Berry");
        for (Shampoo shampoo : this.shampooService.findByIngredientNameIn(ingredients)) {
            System.out.println(shampoo);
        }
    }

    private void e1FindBySize() {
        Size size = Size.valueOf("SMALL");
        Size sizeM = Size.valueOf("MEDIUM");
        for (Shampoo shampoo : this.shampooService.findByBrandAndSize("Cotton Fresh", size)) {
            System.out.println(shampoo);
        }


        for (Shampoo shampoo : this.shampooService.findBySize(size)) {
            System.out.println(shampoo);
        }
        for (Shampoo shampoo : this.shampooService.findBySizeOrderByIdAsc(sizeM)) {
            System.out.println(shampoo);
        }
    }

    private void e1FindByBrand() {
        for (Shampoo shampoo : this.shampooService.findByBrand("Swiss Green Apple & Nettle")) {
            System.out.println(shampoo.getLabel().getTitle());
        }
    }
}
