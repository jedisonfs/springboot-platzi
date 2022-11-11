package com.education.platzicurso.persistence.mapper;

import com.education.platzicurso.domain.CategoryDTO;
import com.education.platzicurso.persistence.entity.Category;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Component("CategoryMapper")
@Mapper(componentModel = "spring")
public interface CategoryMapper {


    @Mapping(source = "category.categoryId", target = "categoryId")
    @Mapping(target = "description", ignore = false)
    @Mapping(source = "state", target = "active")
    CategoryDTO toCategoryDto(Category category);

    @InheritConfiguration
    @Mapping(target = "product", ignore = true)
    Category toCategory(CategoryDTO categoryDTO);
}
