package com.api.financa.controller;

import com.api.financa.model.entity.Despesa;
import com.api.financa.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/despesa")
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @GetMapping("/all")
    public ResponseEntity<List<Despesa>> readAllDespesa(){
        return ResponseEntity.status(HttpStatus.OK).body(despesaService.findAll());
    }

    //TODO: RETORNAR DESPESAS USANDO CATEGORIA COMO PARAMETRO

}
