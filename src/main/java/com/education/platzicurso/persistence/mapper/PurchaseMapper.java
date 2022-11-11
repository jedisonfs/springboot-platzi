package com.education.platzicurso.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.education.platzicurso.domain.PurchaseDTO;
import com.education.platzicurso.persistence.entity.Purchase;
import com.education.platzicurso.persistence.mapper.PurchaseItemMapper;
import com.education.platzicurso.persistence.entity.Purchase;
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
	PurchaseDTO toPurchaseDto(Purchase purchase);
	List<PurchaseDTO> toPurchases(List<Purchase> purchases);

	@InheritInverseConfiguration
	@Mapping(target = "client", ignore = true)
	Purchase toPurchase(PurchaseDTO purchaseDTO);
			
}


