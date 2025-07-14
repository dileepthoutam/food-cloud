package foods.kitchen.messaging.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import foods.kitchen.KitchenUI;
import foods.FoodOrder;

@Profile("kafka-listener")
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderListener {

    private final KitchenUI ui;

    @KafkaListener(topics = "foodcloud.orders.topic", groupId = "food-group")
    public void receiveOrder(FoodOrder order, ConsumerRecord<String, FoodOrder> record) {
        log.info("Received from partition {} with timestamp {}", record.partition(), record.timestamp());
        ui.displayOrder(order);
    }

}