package com.education.platzicurso.persistence.crud;

import java.util.Optional;

import com.education.platzicurso.persistence.entity.PurchaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompraCrudRepository extends CrudRepository<PurchaseEntity, Integer> {

	public Optional<PurchaseEntity> findByClientId(String clientId);

}
