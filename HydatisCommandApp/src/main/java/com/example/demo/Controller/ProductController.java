package com.example.demo.Controller;


import com.example.demo.Models.Product;
import com.example.demo.Models.ProductEvent;
import com.example.demo.Repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository repository;
    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;
    public ProductController(ProductRepository repository, KafkaTemplate<String, ProductEvent> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }
        @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product = repository.save(product);
        ProductEvent event = new ProductEvent("ProductCreated", product);
        this.kafkaTemplate.send("products", event);
        return ResponseEntity.ok().body(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    Product savedProduct = repository.save(product);
                    ProductEvent event = new ProductEvent("ProductUpdated", savedProduct);
                    kafkaTemplate.send("products", event);
                    return ResponseEntity.ok().body(savedProduct);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        return repository.findById(id)
                .map(product -> {
                    repository.delete(product);
                    ProductEvent event = new ProductEvent("ProductDeleted", product);
                    kafkaTemplate.send("products", event);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
