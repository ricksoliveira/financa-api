package com.api.financa.controller;

import com.api.financa.dto.SomaMesesDto;
import com.api.financa.service.SomaMesesService;
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
@RequestMapping("/api/graficos")
public class SomaMesesController {

    @Autowired
    SomaMesesService somaMesesService;

    @GetMapping("/all")
    public ResponseEntity<List<SomaMesesDto>> readAllSomaMeses(){
        return ResponseEntity.status(HttpStatus.OK).body(somaMesesService.findAll());
    }
}
