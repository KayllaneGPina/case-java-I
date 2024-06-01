package br.com.prospertech.case_java.Repository;

import br.com.prospertech.case_java.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
