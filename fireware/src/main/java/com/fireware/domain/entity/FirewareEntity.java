package com.fireware.domain.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.function.BooleanSupplier;
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fireware", schema = "fireware")
public class FirewareEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "firetype")
    private String fireType;
    @Column(name = "fireforce")
    private String fireForce;
    @Column(name = "child", nullable = true)
    private Integer child;
    private Integer teachers;
    private Integer floor;
    private Integer extinguishers;
    @Column(name = "fireoutscount", nullable = true)
    private Integer fireOutsCount;
    private Double square;
    @Column(name = "watersupply", nullable = true)
    private Boolean waterSupply;
    @Column(name = "emergencyfast", nullable = true)
    private Boolean emergencyFast;

}
