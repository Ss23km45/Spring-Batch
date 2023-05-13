/*
    Author Mukesh_Challa
    This is an Entity Class which is like a skeleton of the DB Table
    We have to specify the NONNULL as @Id and remaining Values as @Column
    Lombok is used here to reduce the number of Lines of Code
    @Data will Creates Getters/Setters for this variables
    @AllArgsConstructor With All Arguments
    @NoArgsConstructor With No Arguments
 */
package com.coding.ninja.spring.batch.SpringBatch.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consumers_price_index")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumersPriceIndex {

    //private static final Stirng TABLE_NAME = "${spring.table.name}";


    @Column
    private String Series_reference;
    @Column
    private String Period;
    @Column
    private String Data_value;
    @Column
    private String STATUS_status;
    @Column
    private String UNITS_units;
    @Column
    private String Subject_subject;
    @Column
    private String Group_grp;
    @Column
    private String Series_title_1;
    @Id
    @Column
    private int UniqueNumber;
}
