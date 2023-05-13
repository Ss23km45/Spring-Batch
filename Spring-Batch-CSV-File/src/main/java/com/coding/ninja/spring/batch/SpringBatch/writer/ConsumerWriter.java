package com.coding.ninja.spring.batch.SpringBatch.writer;

import com.coding.ninja.spring.batch.SpringBatch.entitiy.ConsumersPriceIndex;
import com.coding.ninja.spring.batch.SpringBatch.repository.ConsumersPriceIndexRepo;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerWriter implements ItemWriter<ConsumersPriceIndex> {

    @Autowired
    private ConsumersPriceIndexRepo indexRepo;
    private int i =0;

    @Override
    public void write(Chunk<? extends ConsumersPriceIndex> chunk) throws Exception {
        System.out.println("Writer as Called " + i++);
        indexRepo.saveAll(chunk);
        System.out.println("Writer as Called " + i++);
    }


}
