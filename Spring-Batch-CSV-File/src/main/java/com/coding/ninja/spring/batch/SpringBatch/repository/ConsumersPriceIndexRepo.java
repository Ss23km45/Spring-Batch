/*
    Our Repo Class which will communicate with DataBase to do CRUD Operations
    JpaRepository takes two generic arguments Entity Class and ID type
 */
package com.coding.ninja.spring.batch.SpringBatch.repository;

import com.coding.ninja.spring.batch.SpringBatch.entitiy.ConsumersPriceIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumersPriceIndexRepo extends JpaRepository<ConsumersPriceIndex, Long> {
}
