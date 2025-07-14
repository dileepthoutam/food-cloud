package foods.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import foods.Food;

public interface FoodRepository extends PagingAndSortingRepository<Food, Long> {
}
