package com.education.platzicurso.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.education.platzicurso.domain.PurchaseDTO;
import com.education.platzicurso.domain.repository.PurchaseRepository;
import com.education.platzicurso.persistence.crud.CompraCrudRepository;
import com.education.platzicurso.persistence.entity.PurchaseEntity;
import com.education.platzicurso.persistence.mapper.PurchaseMapper;

@Repository
public class CompraRepository implements PurchaseRepository {

	private final CompraCrudRepository compraCrudRepository;
	private final PurchaseMapper purchaseMapper;

    public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper purchaseMapper) {
        this.compraCrudRepository = compraCrudRepository;
        this.purchaseMapper = purchaseMapper;
    }

    @Override
	public List<PurchaseDTO> getAll() {
		return purchaseMapper.toPurchases((List<PurchaseEntity>) compraCrudRepository.findAll());
	}

	@Override
	public Optional<PurchaseDTO> getByClient(String clienteId) {
		return compraCrudRepository.findByClientId(clienteId).map(compras -> purchaseMapper.toPurchaseDto(compras));
	}

	@Override
	public PurchaseDTO save(PurchaseDTO purchaseDTO) {
		PurchaseEntity purchaseEntity = purchaseMapper.toPurchase(purchaseDTO);
		purchaseEntity.getProducts().forEach(producto -> producto.setPurchase(purchaseEntity));

		return purchaseMapper.toPurchaseDto(purchaseEntity);
	}

}
