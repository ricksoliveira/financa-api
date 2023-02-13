package com.api.financa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto {

    private Long categoria_id;
    private String categoria_nome;
    private String categoria_codigo;

}
