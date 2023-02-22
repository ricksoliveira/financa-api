package com.api.financa.service;

import com.api.financa.model.entity.Despesa;
import com.api.financa.repository.DespesaRepository;
import com.api.financa.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DespesaService {

    @Autowired
    DespesaRepository despesaRepository;

    public Map<String, Object> test() {
        Map<String, Object> testResponse = new HashMap<>();
        testResponse.put("teste", "seu valor aqui");
        return testResponse;
    }

    private double getSumValorDespesa(List<Despesa> desp){
        double total = 0.0;
        for(Despesa d : desp){
            total += d.getValor();
        }
        return total;
    }

    public Map<String, Object> findAll() {

        List<Despesa> despesas = despesaRepository.findAll();
        List<String> meses = despesaRepository.getDespesaMeses();
        Map<String, Object> despesasResponse = new HashMap<>();

        despesasResponse.put("meses", meses);
        despesasResponse.put("despesas", despesas);

        double total = this.getSumValorDespesa((List<Despesa>) despesasResponse.get("despesas"));

        despesasResponse.put("size", despesas.size());
        despesasResponse.put("total", total);

        return despesasResponse;
    }

    public Map<String, Object> readDespesaByCategoriaCurrentMonth(int categoria_id) {

        String queryString = Utils.DateUtils.getCurrentDateQueryString();
        List<Despesa> despesas = despesaRepository.readDespesaByMesAndCategoria(queryString, categoria_id);
        Map<String, Object> despesasResponse = new HashMap<>();

        despesasResponse.put("despesas", despesas);

        double total = this.getSumValorDespesa((List<Despesa>) despesasResponse.get("despesas"));

        despesasResponse.put("size", despesas.size());
        despesasResponse.put("total", total);

        return despesasResponse;
    }

    public Map<String, Object> readAllDespesaCurrentMonth() {

        String queryString = Utils.DateUtils.getCurrentDateQueryString();
        List<Despesa> despesas = despesaRepository.readAllDespesaByMes(queryString);
        Map<String, Object> despesasResponse = new HashMap<>();

        despesasResponse.put("despesas", despesas);

        double total = this.getSumValorDespesa((List<Despesa>) despesasResponse.get("despesas"));

        despesasResponse.put("size", despesas.size());
        despesasResponse.put("total", total);

        return despesasResponse;
    }


    public Map<String, Object> readDespesaByMesAndCategoria(String date, int categoria_id){

        List<Despesa> despesas = despesaRepository.readDespesaByMesAndCategoria(date, categoria_id);
        Map<String, Object> despesasResponse = new HashMap<>();

        despesasResponse.put("despesas", despesas);

        double total = this.getSumValorDespesa((List<Despesa>) despesasResponse.get("despesas"));

        despesasResponse.put("size", despesas.size());
        despesasResponse.put("total", total);

        return despesasResponse;
    }

    public Map<String, Object> readDespesaByMes(String data_ref){

        List<Despesa> despesas = despesaRepository.readAllDespesaByMes(data_ref);
        Map<String, Object> despesasResponse = new HashMap<>();

        despesasResponse.put("despesas", despesas);

        double total = this.getSumValorDespesa((List<Despesa>) despesasResponse.get("despesas"));

        despesasResponse.put("size", despesas.size());
        despesasResponse.put("total", total);

        return despesasResponse;
    }


}
