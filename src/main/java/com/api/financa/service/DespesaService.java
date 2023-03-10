package com.api.financa.service;

import com.api.financa.model.entity.Despesa;
import com.api.financa.repository.DespesaRepository;
import com.api.financa.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
        log.debug("Calculando soma das despesas: [{}]", desp);
        double total = 0.0;
        for(Despesa d : desp){
            total += d.getValor();
        }
        log.debug("Soma: [{}] das despesas: [{}]", total, desp);
        return total;
    }

    public Map<String, Object> findAll() {

        log.info("Procurando todas as Despesas");
        List<Despesa> despesas = despesaRepository.findAll();
        if(despesas.isEmpty()){
            log.error("Nenhuma despesa encontrada!");
            return null;
        }
        log.debug("Despesas encontradas: [{}]", despesas);


        log.info("Procurando Meses das despesas");
        List<String> meses = despesaRepository.getDespesaMeses();
        if(meses.isEmpty()){
            log.error("Nenhum mes encontrado!");
            return null;
        }
        log.debug("Meses encontrados: [{}]", meses);


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

        log.debug("Procurando as Despesas da Categoria de ID [{}] do mes atual: [{}]", categoria_id, queryString);
        List<Despesa> despesas = despesaRepository.readDespesaByMesAndCategoria(queryString, categoria_id);
        if(despesas.isEmpty()){
            log.error("Nenhuma despesa encontrada!");
            return null;
        }
        log.debug("Despesas da categoria ID [{}] do mes [{}] encontradas: [{}]", categoria_id, queryString, despesas);


        Map<String, Object> despesasResponse = new HashMap<>();
        despesasResponse.put("despesas", despesas);

        double total = this.getSumValorDespesa((List<Despesa>) despesasResponse.get("despesas"));

        despesasResponse.put("size", despesas.size());
        despesasResponse.put("total", total);

        return despesasResponse;
    }

    public Map<String, Object> readAllDespesaCurrentMonth() {

        String queryString = Utils.DateUtils.getCurrentDateQueryString();

        log.debug("Procurando todas as Despesas do mes atual: [{}]", queryString);
        List<Despesa> despesas = despesaRepository.readAllDespesaByMes(queryString);
        if(despesas.isEmpty()){
            log.error("Nenhuma despesa encontrada!");
            return null;
        }
        log.debug("Despesas do mes atual [{}] encontradas: [{}]", queryString, despesas);


        Map<String, Object> despesasResponse = new HashMap<>();
        despesasResponse.put("despesas", despesas);

        double total = this.getSumValorDespesa((List<Despesa>) despesasResponse.get("despesas"));

        despesasResponse.put("size", despesas.size());
        despesasResponse.put("total", total);

        return despesasResponse;
    }


    public Map<String, Object> readDespesaByMesAndCategoria(String date, int categoria_id){

        log.debug("Procurando despesas de categoria ID [{}] do mes [{}]", categoria_id, date);

        List<Despesa> despesas = despesaRepository.readDespesaByMesAndCategoria(date, categoria_id);
        if(despesas.isEmpty()){
            log.error("Nenhuma despesa encontrada!");
            return null;
        }
        log.debug("Despesas do mes [{}] de categoria ID [{}] encontradas: [{}]", date, categoria_id, despesas);


        Map<String, Object> despesasResponse = new HashMap<>();
        despesasResponse.put("despesas", despesas);

        double total = this.getSumValorDespesa((List<Despesa>) despesasResponse.get("despesas"));

        despesasResponse.put("size", despesas.size());
        despesasResponse.put("total", total);

        return despesasResponse;
    }

    public Map<String, Object> readDespesaByMes(String data_ref){

        log.debug("Procurando todas despesas do mes [{}]", data_ref);
        List<Despesa> despesas = despesaRepository.readAllDespesaByMes(data_ref);
        if(despesas.isEmpty()){
            log.error("Nenhuma despesa encontrada!");
            return null;
        }
        log.debug("Despesas do mes [{}] encontradas: [{}]", data_ref, despesas);


        Map<String, Object> despesasResponse = new HashMap<>();
        despesasResponse.put("despesas", despesas);

        double total = this.getSumValorDespesa((List<Despesa>) despesasResponse.get("despesas"));

        despesasResponse.put("size", despesas.size());
        despesasResponse.put("total", total);

        return despesasResponse;
    }


    @Transactional
    public Despesa saveDespesa(Despesa despesa) {
        log.debug("Salvando nova Despesa: [{}]", despesa);
        return despesaRepository.save(despesa);
    }
}
