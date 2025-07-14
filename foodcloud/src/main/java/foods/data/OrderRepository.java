package foods.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import foods.FoodOrder;

public interface OrderRepository extends CrudRepository<FoodOrder, Long> {

    FoodOrder save(FoodOrder order);

    Optional<FoodOrder> findById(Long id);

}