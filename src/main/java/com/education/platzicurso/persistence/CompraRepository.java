package com.education.platzicurso.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.education.platzicurso.domain.PurchaseDTO;
import com.education.platzicurso.domain.repository.PurchaseRepository;
import com.education.platzicurso.persistence.crud.CompraCrudRepository;
import com.education.platzicurso.persistence.entity.Purchase;
import com.education.platzicurso.persistence.mapper.PurchaseMapper;

@Repository
public class CompraRepository implements PurchaseRepository {

	@Autowired
	private CompraCrudRepository compraCrudRepository;

	private PurchaseMapper purchaseMapper;

	@Override
	public List<PurchaseDTO> getAll() {
		return purchaseMapper.toPurchases((List<Purchase>) compraCrudRepository.findAll());
	}

	@Override
	public Optional<PurchaseDTO> getByClient(String clienteId) {
		return compraCrudRepository.findByClientId(clienteId).map(compras -> purchaseMapper.toPurchaseDto(compras));
	}

	@Override
	public PurchaseDTO save(PurchaseDTO purchaseDTO) {
		Purchase purchase = purchaseMapper.toPurchase(purchaseDTO);
		purchase.getProducts().forEach(producto -> producto.setPurchase(purchase));

		return purchaseMapper.toPurchaseDto(purchase);
	}

}
