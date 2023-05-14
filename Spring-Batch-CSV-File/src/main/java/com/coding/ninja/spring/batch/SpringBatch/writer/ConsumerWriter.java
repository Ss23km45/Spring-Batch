package com.coding.ninja.spring.batch.SpringBatch.writer;

import com.coding.ninja.spring.batch.SpringBatch.entitiy.ConsumersPriceIndex;
import com.coding.ninja.spring.batch.SpringBatch.entitiy.ModelClass;
import com.coding.ninja.spring.batch.SpringBatch.repository.ConsumersPriceIndexRepo;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumerWriter implements ItemWriter<ConsumersPriceIndex> {

    @Autowired
    private ConsumersPriceIndexRepo indexRepo;

    @Autowired
    private ModelClass modelClass;
    private int i =0;

    @Override
    public void write(Chunk<? extends ConsumersPriceIndex> chunk) throws Exception {
        indexRepo.saveAll(chunk);
    }


}
