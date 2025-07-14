package foods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import foods.Ingredient.Type;
import foods.data.IngredientRepository;
import foods.data.UserRepository;

@SpringBootApplication   // <1>
@Slf4j
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    @Profile("default")
    @Order(Integer.MAX_VALUE-1)
    public CommandLineRunner dataLoader(IngredientRepository ingrRepo, UserRepository userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            ingrRepo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            ingrRepo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            ingrRepo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            ingrRepo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            ingrRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            ingrRepo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            ingrRepo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            ingrRepo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            ingrRepo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            ingrRepo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

            userRepo.save(new User("dima", passwordEncoder.encode("pass"), "Dmitrii Dementev",
                    "Aero", "Saint-P", "RU", "150189", "+79205678013"));
        };
    }
}
