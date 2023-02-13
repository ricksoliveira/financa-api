package com.api.financa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SomaMeses")
public class SomaMeses {

    @Id
    private String mes;

    @Column(precision = 9)
    private double receita;
    @Column(precision = 9)
    private double despesa;
    @Column(precision = 9)
    private double uber;
    @Column(precision = 9)
    private double mantimento;
    @Column(precision = 9)
    private double conta;
    @Column(precision = 9)
    private double outros;
    @Column(precision = 9)
    private double lucro;

}
