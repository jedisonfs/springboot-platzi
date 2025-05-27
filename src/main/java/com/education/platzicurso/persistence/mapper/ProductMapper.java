package com.education.platzicurso.persistence.mapper;

import java.util.List;

import com.education.platzicurso.persistence.entity.Category;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import com.education.platzicurso.domain.CategoryDTO;
import com.education.platzicurso.domain.ProductDTO;
import com.education.platzicurso.persistence.entity.Product;

@Component(value = "ProductMapper")
@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({@Mapping(source = "productId", target = "productId"),
            @Mapping(source = "product.name", target = "name"),
            @Mapping(source = "product.categoryId", target = "categoryId"),
            @Mapping(source = "product.salePrice", target = "price"),
            @Mapping(source = "product.quantityStock", target = "stock"),
            @Mapping(source = "product.state", target = "active"),
            @Mapping(source = "category.description", target = "descriptionCategory"),
            @Mapping(source = "category.state", target = "stateCategory")
    })
    ProductDTO toProductDto(Product product, Category category);

    List<ProductDTO> toProducts(List<Product> products);

    @InheritConfiguration
    @Mapping(target = "barcode", ignore = true)
    Product toProduct(ProductDTO productDTO);
}
