package com.coding.ninja.spring.batch.SpringBatch.processor;

import com.coding.ninja.spring.batch.SpringBatch.entitiy.ConsumersPriceIndex;
import org.springframework.batch.item.ItemProcessor;

public class ConsumerPriceIndexProcessor implements ItemProcessor<ConsumersPriceIndex, ConsumersPriceIndex> {

    @Override
    public ConsumersPriceIndex process(ConsumersPriceIndex item) throws Exception {
        return item;
    }
}
