package com.api.financa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SomaMesesDto {

    private String mes;
    private double receita;
    private double despesa;
    private double uber;
    private double mantimento;
    private double conta;
    private double outros;

}
