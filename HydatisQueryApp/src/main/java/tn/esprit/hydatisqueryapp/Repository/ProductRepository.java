package tn.esprit.hydatisqueryapp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.hydatisqueryapp.Model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {
}
