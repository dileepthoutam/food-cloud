package foods.kitchen.messaging.jms;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import foods.kitchen.OrderReceiver;
import foods.FoodOrder;

@Profile("jms-template")
@Component
@RequiredArgsConstructor
public class JmsOrderReceiver implements OrderReceiver {

    private final JmsTemplate jms;

    @Override
    public FoodOrder receiveOrder() {
        return (FoodOrder) jms.receiveAndConvert("foodcloud.order.queue");
    }

}