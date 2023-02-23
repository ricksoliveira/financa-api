package com.api.financa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesDto {

    private int mes;
    private int ano;
    private String status;

}
