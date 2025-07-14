package foods.kitchen.messaging.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import foods.kitchen.OrderReceiver;
import foods.FoodOrder;

@Profile("rabbitmq-template")
@Component
@RequiredArgsConstructor
public class RabbitOrderReceiver implements OrderReceiver {
    private final RabbitTemplate rabbit;

    @Override
    public FoodOrder receiveOrder() {
        return rabbit.receiveAndConvert("foodcloud.order.queue", new ParameterizedTypeReference<FoodOrder>() {});
    }
}
