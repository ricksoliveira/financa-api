package com.api.financa.service;

import com.api.financa.model.entity.Despesa;
import com.api.financa.repository.DespesaRepository;
import com.api.financa.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    @Autowired
    DespesaRepository despesaRepository;

    public List<Despesa> findAll() {
        return despesaRepository.findAll();
    }

    public List<Despesa> readDespesaByCategoriaCurrentMonth(int categoria_id) {

        String queryString = Utils.DateUtils.getCurrentDateQueryString();
        List<Despesa> despesas = despesaRepository.readDespesaByMesAndCategoria(queryString, categoria_id);

        double total = 0;
        for(Despesa r : despesas){
            total += r.getValor();
        }
        return despesas;
    }

    public List<Despesa> readAllDespesaCurrentMonth() {

        String queryString = Utils.DateUtils.getCurrentDateQueryString();
        List<Despesa> despesas = despesaRepository.readAllDespesaByMes(queryString);

        double total = 0;
        for(Despesa r : despesas){
            total += r.getValor();
        }
        return despesas;
    }


    public List<Despesa> readDespesaByMesAndCategoria(String date, int categoria_id){
        List<Despesa> despesas = despesaRepository.readDespesaByMesAndCategoria(date, categoria_id);
        double total = 0;
        for(Despesa r : despesas){
            total += r.getValor();
        }
        return despesas;
    }

    public List<Despesa> readDespesaByMes(String data_ref){
        List<Despesa> despesas = despesaRepository.readAllDespesaByMes(data_ref);
        double total = 0;
        for(Despesa r : despesas){
            total += r.getValor();
        }
        return despesas;
    }

}
