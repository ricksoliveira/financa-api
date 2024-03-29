package com.api.financa.repository;

import com.api.financa.model.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa group by year(data_ref), month(data_ref);", nativeQuery = true)
    List<String> getMesesList();

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa where categoria_id in (1, 2, 3, 5) and data_ref like '%202%' group by year(data_ref), month(data_ref);", nativeQuery = true)
    List<String> getDespesaMeses();

    @Query(value = "select sum(valor) as 'despesa' from despesa where categoria_id in (1, 2, 3, 5) and data_ref like '%202%' group by year(data_ref), month(data_ref);", nativeQuery = true)
    List<Double> getDespesaValores();

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa where categoria_id in (1) and data_ref like '%202%' group by year(data_ref), month(data_ref);", nativeQuery = true)
    List<String> getMantimentoMeses();

    @Query(value = "select sum(valor) as 'mantimento' from despesa where categoria_id in (1) and data_ref like '%202%' group by year(data_ref), month(data_ref);", nativeQuery = true)
    List<Double> getMantimentoValores();

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa where categoria_id in (2) and data_ref like '%202%' group by year(data_ref), month(data_ref);", nativeQuery = true)
    List<String> getContaMeses();

    @Query(value = "select sum(valor) as 'conta' from despesa where categoria_id in (2) and data_ref like '%202%' group by year(data_ref), month(data_ref);", nativeQuery = true)
    List<Double> getContaValores();

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa where categoria_id in (5) and data_ref like '%202%' group by year(data_ref), month(data_ref);", nativeQuery = true)
    List<String> getOutrosMeses();

    @Query(value = "select sum(valor) as 'outros' from despesa where categoria_id in (5) and data_ref like '%202%' group by year(data_ref), month(data_ref);", nativeQuery = true)
    List<Double> getOutrosValores();

    @Query(value = "select concat(MONTH(data_ref), '/', year(data_ref)) as 'mes' from despesa where categoria_id in (3) and data_ref like '%202%' group by year(data_ref), month(data_ref);", nativeQuery = true)
    List<String> getUberMeses();

    @Query(value = "select sum(valor) as 'uber' from despesa where categoria_id in (3) and data_ref like '%202%' group by year(data_ref), month(data_ref);", nativeQuery = true)
    List<Double> getUberValores();



    @Query(value = "select * from despesa where categoria_id = :categoria_id and data_ref like :data_ref '%' ;", nativeQuery = true)
    List<Despesa> readDespesaByMesAndCategoria(
            @Param("data_ref") String data_ref,
            @Param("categoria_id") int categoria_id
    );

    @Query(value = "select * from despesa where categoria_id in (1, 2, 3, 5) and data_ref like :data_ref '%' ;", nativeQuery = true)
    List<Despesa> readAllDespesaByMes(
            @Param("data_ref") String data_ref
    );



    @Query(value = "select month(data_ref) as 'mes' from despesa where status = 'Fechado' group by year(data_ref), month(data_ref) order by data_ref;", nativeQuery = true)
    List<Integer> getMesesMes();

    @Query(value = "select year(data_ref) as 'ano' from despesa where status = 'Fechado' group by year(data_ref), month(data_ref) order by data_ref;", nativeQuery = true)
    List<Integer> getMesesAno();

    @Query(value = "select status from despesa where status = 'Fechado' group by year(data_ref), month(data_ref) order by data_ref;", nativeQuery = true)
    List<String> getMesesStatus();



    @Query(value = "select count(*) from despesa where status = 'Aberto';", nativeQuery = true)
    int getQuantidadeDespesaAberta();

    @Query(value = "UPDATE despesa SET status = 'Fechado' WHERE status = 'Aberto';", nativeQuery = true)
    int fecharDespesaMes();




}
