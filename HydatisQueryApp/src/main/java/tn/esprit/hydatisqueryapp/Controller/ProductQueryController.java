package tn.esprit.hydatisqueryapp.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.hydatisqueryapp.Model.Product;
import tn.esprit.hydatisqueryapp.Repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
    private final ProductRepository repository;

    public ProductQueryController(ProductRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Product> getProducts() {

        return new ArrayList<>(repository.findAll());
    }
}
