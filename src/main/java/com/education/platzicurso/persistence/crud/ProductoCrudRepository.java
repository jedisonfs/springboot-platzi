package com.education.platzicurso.persistence.crud;

import com.education.platzicurso.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Product, Integer> {

	// @Query(value = "SELECT * FROM Producto pr WHERE pr.id_categoria = ?1 ORDER BY
	// nombre;")
	List<Product> findByCategoryIdOrderByNameAsc(int categoryId);

	Optional<List<Product>> findByQuantityStockIsLessThanAndState(int cantidadStock, boolean estado);
}
