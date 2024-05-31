package tn.esprit.hydatisqueryapp.Service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tn.esprit.hydatisqueryapp.Model.ProductEvent;
import tn.esprit.hydatisqueryapp.Repository.ProductRepository;
@Service
@RequiredArgsConstructor
public class ProductEventConsumerService {

    private final ProductRepository productRepository;

}

