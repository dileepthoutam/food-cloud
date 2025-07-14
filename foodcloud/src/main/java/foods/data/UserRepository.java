package foods.data;

import org.springframework.data.repository.CrudRepository;
import foods.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}