package com.education.platzicurso.persistence.mapper;

import com.education.platzicurso.domain.CategoryDTO;
import com.education.platzicurso.persistence.entity.CategoryEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component("CategoryMapper")
@Mapper(componentModel = "spring")
public interface CategoryMapper {


    @Mapping(source = "categoryEntity.categoryId", target = "categoryId")
    @Mapping(target = "description", ignore = false)
    @Mapping(source = "state", target = "active")
    CategoryDTO toCategoryDto(CategoryEntity categoryEntity);

    @InheritConfiguration
    @Mapping(target = "product", ignore = true)
    CategoryEntity toCategory(CategoryDTO categoryDTO);
}
