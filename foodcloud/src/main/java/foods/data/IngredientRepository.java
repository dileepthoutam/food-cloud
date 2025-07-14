package foods.data;

import org.springframework.data.repository.CrudRepository;
import foods.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}