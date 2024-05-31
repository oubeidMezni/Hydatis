package tn.esprit.hydatisqueryapp.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import tn.esprit.hydatisqueryapp.Model.ProductEvent;

import java.io.IOException;
import java.util.Map;

public class ProductEventDeserializer implements Deserializer<ProductEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public ProductEvent deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, ProductEvent.class);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing product event", e);
        }
    }

    @Override
    public void close() {
    }
}
