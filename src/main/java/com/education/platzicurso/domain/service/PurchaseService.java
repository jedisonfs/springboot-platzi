package com.education.platzicurso.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.platzicurso.domain.PurchaseDTO;
import com.education.platzicurso.domain.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Transactional(readOnly = true)
	public List<PurchaseDTO> getAll() {
		return purchaseRepository.getAll();
	}

	@Transactional(readOnly = true)
	public Optional<PurchaseDTO> getByClient(String clientId) {
		return purchaseRepository.getByClient(clientId);
	}

	@Transactional(readOnly = false)
	public PurchaseDTO save(PurchaseDTO purchaseDTO) {
		return purchaseRepository.save(purchaseDTO);
	}

}
