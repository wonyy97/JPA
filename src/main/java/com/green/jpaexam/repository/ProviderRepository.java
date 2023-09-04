package com.green.jpaexam.repository;

import com.green.jpaexam.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {
}
