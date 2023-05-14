package com.coding.ninja.spring.batch.SpringBatch.repository;

import com.coding.ninja.spring.batch.SpringBatch.entitiy.ModelClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelClassRepo extends JpaRepository<ModelClass, Integer> {
}
