package com.api.financa.repository;

import com.api.financa.model.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from receita r where r.categoria_id = 4 and r.data_ref like '%202%' group by month(r.data_ref);", nativeQuery = true)
    List<String> getReceitaMeses();

    @Query(value = "select sum(r.valor) as 'receita' from receita r where r.categoria_id = 4 and r.data_ref like '%202%' group by month(r.data_ref);", nativeQuery = true)
    List<Double> getReceitaValores();

}
