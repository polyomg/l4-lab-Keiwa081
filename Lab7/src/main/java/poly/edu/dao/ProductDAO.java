package poly.edu.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poly.edu.entity.Product;
import poly.edu.entity.Report;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
	//=gốc
//	@Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
//	List<Product> findByPrice(double minPrice, double maxPrice);
	
//	// Sắp xếp tăng dần theo giá
	@Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2 ORDER BY o.price ASC")
	List<Product> findByPrice(double minPrice, double maxPrice);

	@Query("FROM Product o WHERE o.name LIKE ?1")
	Page<Product> findByKeywords(String keywords, Pageable pageable);

	@Query("SELECT o.category AS group, SUM(o.price) AS sum, COUNT(o) AS count " + "FROM Product o "
			+ "GROUP BY o.category " + "ORDER BY SUM(o.price) DESC")
	List<Report> getInventoryByCategory();

}
