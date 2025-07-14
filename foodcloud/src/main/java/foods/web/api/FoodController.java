package foods.web.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import foods.data.FoodRepository;
import foods.Food;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/foods",
        produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class FoodController {
    private FoodRepository foodRepo;

    public FoodController(FoodRepository foodRepo) {
        this.foodRepo = foodRepo;
    }

    @GetMapping(params = "recent")
    public Iterable<Food> recentFoods() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return foodRepo.findAll(page).getContent();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Food postFood(@RequestBody Food food) {
        return foodRepo.save(food);
    }

    @GetMapping("/{id}")
    public Food foodById(@PathVariable("id") Long id) {
        Optional<Food> optFood = foodRepo.findById(id);
        if (optFood.isPresent()) {
            return optFood.get();
        }
        return null;
    }

  /*
  @GetMapping("/{id}")
  public ResponseEntity<Food> foodById(@PathVariable("id") Long id) {
    Optional<Food> optFood = foodRepo.findById(id);
    if (optFood.isPresent()) {
      return new ResponseEntity<>(optFood.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
  */

}