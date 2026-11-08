package com.education.platzicurso.persistence.mapper;

import java.util.List;

import com.education.platzicurso.persistence.entity.PurchaseEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.education.platzicurso.domain.PurchaseDTO;

@Component(value = "PurchaseMapper")
@Mapper(componentModel = "spring", uses = { PurchaseItemMapper.class })
public interface PurchaseMapper {

	@Mapping(source = "purchase.purchaseId", target = "purschaseId")
	@Mapping(source = "purchase.clientId", target = "clientId")
	@Mapping(source = "purchase.date", target = "date")
	@Mapping(source = "purchase.paymentMethod", target = "paymentMethod")
	@Mapping(source = "purchase.comment", target = "comment")
	@Mapping(source = "purchase.state", target = "state")
	@Mapping(source = "purchase.products", target = "items") // Este mapea cada uOno de los items de la lista de compras
	PurchaseDTO toPurchaseDto(PurchaseEntity purchaseEntity);
	List<PurchaseDTO> toPurchases(List<PurchaseEntity> purchaseEntities);

	@InheritInverseConfiguration
	@Mapping(target = "client", ignore = true)
    PurchaseEntity toPurchase(PurchaseDTO purchaseDTO);
			
}


