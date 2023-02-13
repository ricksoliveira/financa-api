package com.api.financa.repository;

import com.api.financa.model.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa group by month(data_ref);", nativeQuery = true)
    List<String> getMesesList();

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa where categoria_id in (1, 2, 3, 5) and data_ref like '%202%' group by month(data_ref);", nativeQuery = true)
    List<String> getDespesaMeses();

    @Query(value = "select sum(valor) as 'despesa' from despesa where categoria_id in (1, 2, 3, 5) and data_ref like '%202%' group by month(data_ref);", nativeQuery = true)
    List<Double> getDespesaValores();

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa where categoria_id in (1) and data_ref like '%202%' group by month(data_ref);", nativeQuery = true)
    List<String> getMantimentoMeses();

    @Query(value = "select sum(valor) as 'mantimento' from despesa where categoria_id in (1) and data_ref like '%202%' group by month(data_ref);", nativeQuery = true)
    List<Double> getMantimentoValores();

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa where categoria_id in (2) and data_ref like '%202%' group by month(data_ref);", nativeQuery = true)
    List<String> getContaMeses();

    @Query(value = "select sum(valor) as 'conta' from despesa where categoria_id in (2) and data_ref like '%202%' group by month(data_ref);", nativeQuery = true)
    List<Double> getContaValores();

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa where categoria_id in (5) and data_ref like '%202%' group by month(data_ref);", nativeQuery = true)
    List<String> getOutrosMeses();

    @Query(value = "select sum(valor) as 'outros' from despesa where categoria_id in (5) and data_ref like '%202%' group by month(data_ref);", nativeQuery = true)
    List<Double> getOutrosValores();

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa where categoria_id in (3) and data_ref like '%202%' group by month(data_ref);", nativeQuery = true)
    List<String> getUberMeses();

    @Query(value = "select sum(valor) as 'uber' from despesa where categoria_id in (3) and data_ref like '%202%' group by month(data_ref);", nativeQuery = true)
    List<Double> getUberValores();

}
