package com.education.platzicurso.persistence;

import com.education.platzicurso.domain.ProductDTO;
import com.education.platzicurso.domain.repository.ProductRepostitory;
import com.education.platzicurso.persistence.crud.ProductoCrudRepository;
import com.education.platzicurso.persistence.entity.Product;
import com.education.platzicurso.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepostitory {

	private ProductoCrudRepository productoCrudRepository;

	@Autowired(required = true)
	private ProductMapper productMapper;

	@Override
	public List<ProductDTO> getAll() {
		List<Product> products = (List<Product>) productoCrudRepository.findAll();
		return productMapper.toProducts(products);
	}

	@Override
	public Optional<List<ProductDTO>> getByCategory(int idCategoria) {
		List<Product> products = productoCrudRepository.findByCategoryIdOrderByNameAsc(idCategoria);
		return Optional.of(productMapper.toProducts(products));
	}

	@Override
	public Optional<List<ProductDTO>> getScarseProducts(int quantity) {
		Optional<List<Product>> products = productoCrudRepository.findByQuantityStockIsLessThanAndState(quantity, true);
		return products.map(prods -> productMapper.toProducts(prods));
	}

	@Override
	public Optional<ProductDTO> getProduct(int productId) {
		// Optional<ProductDTO>
		return productoCrudRepository.findById(productId).map(producto -> productMapper.toProductDto(producto));
	}

	@Override
	public ProductDTO save(ProductDTO productDTO) {
		Product product = productMapper.toProduct(productDTO);
		return productMapper.toProductDto(product);
	}

	@Override
	public void delete(int productId) {
		productoCrudRepository.deleteById(productId);
	}

}
