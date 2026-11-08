package com.education.platzicurso.persistence;

import com.education.platzicurso.domain.ProductDTO;
import com.education.platzicurso.domain.repository.ProductRepostitory;
import com.education.platzicurso.persistence.crud.ProductoCrudRepository;
import com.education.platzicurso.persistence.entity.ProductEntity;
import com.education.platzicurso.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepostitory {

	private final ProductoCrudRepository productoCrudRepository;
	private final ProductMapper productMapper;

    public ProductoRepository(ProductoCrudRepository productoCrudRepository, ProductMapper productMapper) {
        this.productoCrudRepository = productoCrudRepository;
        this.productMapper = productMapper;
    }

    @Override
	public List<ProductDTO> getAll() {
		List<ProductEntity> productEntities = (List<ProductEntity>) productoCrudRepository.findAll();
		return productMapper.toProducts(productEntities);
	}

	@Override
	public Optional<List<ProductDTO>> getByCategory(int idCategoria) {
		List<ProductEntity> productEntities = productoCrudRepository.findByCategoryIdOrderByNameAsc(idCategoria);
		return Optional.of(productMapper.toProducts(productEntities));
	}

	@Override
	public Optional<List<ProductDTO>> getScarseProducts(int quantity) {
		Optional<List<ProductEntity>> products = productoCrudRepository.findByQuantityStockIsLessThanAndState(quantity, true);
		return products.map(prods -> productMapper.toProducts(prods));
	}

	@Override
	public Optional<ProductDTO> getProduct(int productId) {
		// Optional<ProductDTO>
		return productoCrudRepository.findById(productId).map(producto -> productMapper.toProductDto(producto));
	}

	@Override
	public ProductDTO save(ProductDTO productDTO) {
		ProductEntity productEntity = productMapper.toProduct(productDTO);
		return productMapper.toProductDto(productEntity);
	}

	@Override
	public void delete(int productId) {
		productoCrudRepository.deleteById(productId);
	}

}
