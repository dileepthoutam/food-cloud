package foods.kitchen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import foods.FoodOrder;

@Component
@Slf4j
public class KitchenUI {

  public void displayOrder(FoodOrder order) {
    // TODO: Beef this up to do more than just log the received food.
    //       To display it in some sort of UI.
    log.info("RECEIVED ORDER:  " + order);
  }
  
}