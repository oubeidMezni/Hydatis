package tn.esprit.hydatisqueryapp.Configuration;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.hydatisqueryapp.Model.ProductEvent;
import tn.esprit.hydatisqueryapp.Repository.ProductRepository;

@Component
public class KafkaListenerConfig {
    @Autowired
    private ProductRepository productRepository;


    @KafkaListener(topics = "products", groupId = "products_group")
public void processProductEvent(ConsumerRecord<String, ProductEvent> record) {
    ProductEvent productEvent = record.value();
    System.out.println("Received event: " + productEvent);
    switch (productEvent.getType()) {
        case "ProductCreated":
            productRepository.save(productEvent.getProduct());
            break;
        case "ProductUpdated":
            productRepository.findById(productEvent.getProduct().getId())
                    .ifPresent(product -> {
                        product.setName(productEvent.getProduct().getName());
                        product.setDescription(productEvent.getProduct().getDescription());
                        product.setPrice(productEvent.getProduct().getPrice());
                        productRepository.save(product);
                    });
            break;
        case "ProductDeleted":
            productRepository.findById(productEvent.getProduct().getId())
                    .ifPresent(productRepository::delete);
            break;
        default:
            System.out.println("Unhandled event type: " + productEvent.getType());
    }
}
}
