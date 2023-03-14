package com.api.financa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long usuario_id;
    private String username;
    private String email;
    private String senha;
    private String nota;
    private String status;

}
