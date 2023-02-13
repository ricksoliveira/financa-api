package com.api.financa.service;

import com.api.financa.model.entity.Receita;
import com.api.financa.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;

    public List<Receita> findAll() {
        return receitaRepository.findAll();
    }
}
