package com.api.financa.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Receita")
public class Receita {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receita_id;

    @Column
    private Long categoria_id;

    @Column(precision = 9)
    private Double valor;

    @Column
    private Date data_ref;

    @Column
    private String nota;

    @Column(length = 25)
    private String status;


}
