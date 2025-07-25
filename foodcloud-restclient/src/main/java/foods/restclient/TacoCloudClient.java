package foods.restclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import foods.Ingredient;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodCloudClient {

    private final RestTemplate rest;

    private final String apiUrl = "http://localhost:8080/api";

    //
    // GET examples
    //

    /*
     * Specify parameter as varargs argument
     */
    public Ingredient getIngredientById(String ingredientId) {
        return rest.getForObject(apiUrl + "/ingredients/{id}",
                Ingredient.class, ingredientId);
    }

    /*
     * Alternate implementations...
     * The next three methods are alternative implementations of
     * getIngredientById() as shown in chapter 6. If you'd like to try
     * any of them out, comment out the previous method and uncomment
     * the variant you want to use.
     */

    /*
     * Specify parameters with a map
     */
  /*
  public Ingredient getIngredientById(String ingredientId) {
    Map<String, String> urlVariables = new HashMap<>();
    urlVariables.put("id", ingredientId);
    return rest.getForObject("http://localhost:8080/ingredients/{id}",
        Ingredient.class, urlVariables);
  }
  */

    /*
     * Request with URI instead of String
     */
  /*
  public Ingredient getIngredientById(String ingredientId) {
    Map<String, String> urlVariables = new HashMap<>();
    urlVariables.put("id", ingredientId);
    URI url = UriComponentsBuilder
              .fromHttpUrl("http://localhost:8080/ingredients/{id}")
              .build(urlVariables);
    return rest.getForObject(url, Ingredient.class);
  }
  */

    /*
     * Use getForEntity() instead of getForObject()
     */
  /*
  public Ingredient getIngredientById(String ingredientId) {
    ResponseEntity<Ingredient> responseEntity =
        rest.getForEntity("http://localhost:8080/ingredients/{id}",
            Ingredient.class, ingredientId);
    log.info("Fetched time: {}",
            responseEntity.getHeaders().getDate());
    return responseEntity.getBody();
  }
  */

    public List<Ingredient> getAllIngredients() {
        return rest.exchange(apiUrl + "/ingredients",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>() {
                })
                .getBody();
    }

    //
    // PUT examples
    //

    public void updateIngredient(Ingredient ingredient) {
        rest.put(apiUrl + "/ingredients/{id}",
                ingredient, ingredient.getId());
    }

    //
    // POST examples
    //
    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject(apiUrl + "/ingredients",
                ingredient, Ingredient.class);
    }

    /*
     * Alternate implementations...
     * The next two methods are alternative implementations of
     * createIngredient() as shown in chapter 6. If you'd like to try
     * any of them out, comment out the previous method and uncomment
     * the variant you want to use.
     */
  /*
  public java.net.URI createIngredient(Ingredient ingredient) {
    return rest.postForLocation("http://localhost:8080/ingredients",
        ingredient);
  }
  */

  /*
  public Ingredient createIngredient(Ingredient ingredient) {
    ResponseEntity<Ingredient> responseEntity =
           rest.postForEntity("http://localhost:8080/ingredients",
                              ingredient,
                              Ingredient.class);
    log.info("New resource created at {}",
             responseEntity.getHeaders().getLocation());
    return responseEntity.getBody();
  }
  */

    //
    // DELETE examples
    //

    public void deleteIngredient(Ingredient ingredient) {
        rest.delete(apiUrl + "/ingredients/{id}",
                ingredient.getId());
    }

}