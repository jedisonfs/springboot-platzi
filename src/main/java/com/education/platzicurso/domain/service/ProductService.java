package com.education.platzicurso.domain.service;

import com.education.platzicurso.domain.ProductDTO;
import com.education.platzicurso.domain.repository.ProductRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepostitory productRepostitory;

    @Transactional(readOnly = true)
    public List<ProductDTO> getAll(){
        return productRepostitory.getAll();
    }

    @Transactional(readOnly = true)
    public Optional<ProductDTO> getProduct(int productId){
        return productRepostitory.getProduct(productId);
    }

    @Transactional(readOnly = true)
    public Optional<List<ProductDTO>> getByCategory(int categoryId) {
        return productRepostitory.getByCategory(categoryId);
    }

    @Transactional(readOnly = false)
    public ProductDTO save(ProductDTO productDTO){
        return productRepostitory.save(productDTO);
    }

    
    public boolean delete(int productId){
        return (Boolean) getProduct(productId).map(product -> {
            productRepostitory.delete(productId);
            return true;
        }).orElse(false);
    }


}
