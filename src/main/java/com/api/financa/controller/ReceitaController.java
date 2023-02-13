package com.api.financa.controller;

import com.api.financa.model.entity.Receita;
import com.api.financa.service.ReceitaService;
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
@RequestMapping("/api/receita")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @GetMapping("/all")
    public ResponseEntity<List<Receita>> readAllReceita(){
        return ResponseEntity.status(HttpStatus.OK).body(receitaService.findAll());
    }

}
