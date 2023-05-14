package com.coding.ninja.spring.batch.SpringBatch.processor;

import com.coding.ninja.spring.batch.SpringBatch.entitiy.ConsumersPriceIndex;
import com.coding.ninja.spring.batch.SpringBatch.entitiy.ModelClass;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsumerModelProcess implements ItemProcessor<ConsumersPriceIndex, ModelClass> {

    @Override
    public ModelClass process(ConsumersPriceIndex item) throws Exception {
        return itemToBeProcessed(item);
    }

    private ModelClass itemToBeProcessed(ConsumersPriceIndex item) {
        ModelClass modelClass = new ModelClass();
        modelClass.setData_Value(item.getData_value());
        modelClass.setGroup_grp(item.getGroup_grp());
        modelClass.setUniqueNumber(item.getUniqueNumber());

        return modelClass;
    }
}
