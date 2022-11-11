package com.education.platzicurso.domain.repository;

import java.util.List;
import java.util.Optional;

import com.education.platzicurso.domain.PurchaseDTO;

public interface PurchaseRepository {

	List<PurchaseDTO> getAll();

	Optional<PurchaseDTO> getByClient(String clienteId);

	PurchaseDTO save(PurchaseDTO purchasegit );
}
