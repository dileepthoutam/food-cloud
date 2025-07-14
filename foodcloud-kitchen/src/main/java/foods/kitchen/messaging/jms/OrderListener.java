package foods.kitchen.messaging.jms;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import foods.kitchen.KitchenUI;
import foods.FoodOrder;

@Profile("jms-listener")
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final KitchenUI ui;

    @JmsListener(destination = "foodcloud.order.queue")
    public void receiveOrder(FoodOrder order) {
        ui.displayOrder(order);
    }

}