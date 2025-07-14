package foods.messaging;

import foods.FoodOrder;

public interface OrderMessagingService {

  void sendOrder(FoodOrder order);
  
}