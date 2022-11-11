package com.education.platzicurso.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.education.platzicurso.domain.PurchaseItemDTO;
import com.education.platzicurso.persistence.entity.PurchaseItem;

@Component(value = "PurchaseItempMapper")
@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mapping(source = "purchaseItem.id.productId", target = "productId")
    @Mapping(source = "purchaseItem.quantity", target = "quantity")
    @Mapping(source = "purchaseItem.total", target = "total")
    @Mapping(source = "purchaseItem.state", target = "active")
    PurchaseItemDTO toPurcharseItemDto(PurchaseItem purchaseItem);

    @InheritInverseConfiguration
    @Mapping(target = "purchase", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "id.purchaseId", ignore = true)
    PurchaseItem toPurchaseItem(PurchaseItemDTO purchaseItemDTO);

}
