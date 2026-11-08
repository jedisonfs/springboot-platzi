package com.education.platzicurso.persistence.crud;

import com.education.platzicurso.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<ProductEntity, Integer> {

	// @Query(value = "SELECT * FROM Producto pr WHERE pr.id_categoria = ?1 ORDER BY
	// nombre;")
	List<ProductEntity> findByCategoryIdOrderByNameAsc(int categoryId);

	Optional<List<ProductEntity>> findByQuantityStockIsLessThanAndState(int cantidadStock, boolean estado);
}
