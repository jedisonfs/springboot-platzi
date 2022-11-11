package com.education.platzicurso.persistence.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.education.platzicurso.persistence.entity.Purchase;

public interface CompraCrudRepository extends CrudRepository<Purchase, Integer> {

	public Optional<Purchase> findByClientId(String clientId);

}
