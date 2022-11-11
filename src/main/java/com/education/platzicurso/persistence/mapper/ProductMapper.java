package com.education.platzicurso.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import com.education.platzicurso.domain.CategoryDTO;
import com.education.platzicurso.domain.ProductDTO;
import com.education.platzicurso.persistence.entity.Product;

@Component(value = "ProductMapper")
@Mapper(componentModel = "spring", uses = {CategoryDTO.class})
public interface ProductMapper {

    @Mappings({@Mapping(source = "productId", target = "productId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "categoryId", target = "categoryId"),
            @Mapping(source = "salePrice", target = "price"),
            @Mapping(source = "quantityStock", target = "stock"),
            @Mapping(source = "state", target = "active"),
            @Mapping(source = "category", target = "categoryDto")})
    ProductDTO toProductDto(Product product);

    List<ProductDTO> toProducts(List<Product> products);

    @InheritConfiguration
    @Mapping(target = "barcode", ignore = true)
    Product toProduct(ProductDTO productDTO);
}
