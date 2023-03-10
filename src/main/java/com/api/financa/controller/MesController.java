package com.api.financa.controller;

import com.api.financa.service.MesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/mes")
public class MesController {

    @Autowired
    MesService mesService;

    @GetMapping("/groupmonths")
    public ResponseEntity<Map<String, Object>> listGroupByMonths(){
        return ResponseEntity.status(HttpStatus.OK).body(mesService.listGroupByMonths());
    }

}
