package com.api.financa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DespesaDto {

    private Long despesa_id;
    private Long categoria_id;
    private Double valor;
    private Date data_ref;
    private String nota;
    private String status;
}
