package foods.kitchen.messaging.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import foods.kitchen.KitchenUI;
import foods.FoodOrder;

@Profile("rabbitmq-listener")
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final KitchenUI ui;

    @RabbitListener(queues = "foodcloud.order.queue")
    public void receiveOrder(FoodOrder order) {
        ui.displayOrder(order);
    }

}