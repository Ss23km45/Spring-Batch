package com.coding.ninja.spring.batch.SpringBatch.writer;

import com.coding.ninja.spring.batch.SpringBatch.entitiy.ModelClass;
import com.coding.ninja.spring.batch.SpringBatch.repository.ModelClassRepo;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelWriter implements ItemWriter<ModelClass> {
    @Autowired
    private ModelClassRepo modelClassRepo;
    @Override
    public void write(Chunk<? extends ModelClass> chunk) throws Exception {
        modelClassRepo.saveAll(chunk);
    }
}
