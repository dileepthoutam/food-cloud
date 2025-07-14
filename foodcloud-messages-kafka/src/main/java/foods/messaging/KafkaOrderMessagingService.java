package foods.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import foods.FoodOrder;

@Service
@RequiredArgsConstructor
public class KafkaOrderMessagingService implements OrderMessagingService {

    @Autowired
    private KafkaTemplate<String, FoodOrder> kafkaTemplate;

    @Override
    public void sendOrder(FoodOrder order) {
        kafkaTemplate.send("foodcloud.orders.topic", order);
    }

}