package foods.messaging;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import foods.FoodOrder;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties") // собственные properties в модуле, yml нельзя
public class MessagingConfig {

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter =
                new MappingJackson2MessageConverter();
        messageConverter.setTargetType(MessageType.TEXT);
        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order", FoodOrder.class);
        messageConverter.setTypeIdMappings(typeIdMappings);

        return messageConverter;
    }

    @Value("${spring.jms.template.default-destination}")
    public String orderQueue;

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue(orderQueue);
    }

}