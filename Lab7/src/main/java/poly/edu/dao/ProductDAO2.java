package poly.edu.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import poly.edu.entity.Product;



@Repository
public interface ProductDAO2 extends JpaRepository<Product, Integer> {

	// Bai 4
	List<Product> findByPriceBetween(double minPrice, double maxPrice);
	
	// Bai 5 
	Page<Product> findAllByNameLike(String keywords, Pageable pageable);

}
