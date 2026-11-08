package com.education.platzicurso.persistence.mapper;

import com.education.platzicurso.persistence.entity.PurchaseItemEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.education.platzicurso.domain.PurchaseItemDTO;

@Component(value = "PurchaseItempMapper")
@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mapping(source = "purchaseItemEntity.id.productId", target = "productId")
    @Mapping(source = "purchaseItemEntity.quantity", target = "quantity")
    @Mapping(source = "purchaseItemEntity.total", target = "total")
    @Mapping(source = "purchaseItemEntity.state", target = "active")
    PurchaseItemDTO toPurcharseItemDto(PurchaseItemEntity purchaseItemEntity);

    @InheritInverseConfiguration
    @Mapping(target = "purchase", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "id.purchaseId", ignore = true)
    PurchaseItemEntity toPurchaseItem(PurchaseItemDTO purchaseItemDTO);

}
