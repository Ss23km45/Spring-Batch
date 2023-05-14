package com.coding.ninja.spring.batch.SpringBatch.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Consumers_Price")
@Data
@Component
public class ModelClass {

    @Column
    private String data_Value;
    @Id
    @Column
    private int UniqueNumber;

    @Column
    private String group_grp;

}
