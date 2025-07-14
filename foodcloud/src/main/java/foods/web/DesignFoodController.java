package foods.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import foods.data.IngredientRepository;
import foods.Ingredient;
import foods.Ingredient.Type;
import foods.Food;
import foods.FoodOrder;
import foods.User;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("foodOrder")
@RequiredArgsConstructor
public class DesignFoodController {

    private final IngredientRepository ingredientRepo;

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepo.findAll();

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "foodOrder")
    public FoodOrder order() {
        return new FoodOrder();
    }

    @ModelAttribute(name = "food")
    public Food food() {
        return new Food();
    }

    @ModelAttribute(name = "user")
    public User user(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processFood(@Valid Food food, Errors errors,
                              @ModelAttribute FoodOrder foodOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        foodOrder.addFood(food);
        log.info("Processing food: {}", food);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}