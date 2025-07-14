package foods;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Food {
    private Long id;

    private Date createdAt = new Date();

    private String name;

    private List<Ingredient> ingredients;
}
