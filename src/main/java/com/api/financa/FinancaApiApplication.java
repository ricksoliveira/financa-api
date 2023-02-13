package com.api.financa;

import com.api.financa.model.entity.Receita;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FinancaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancaApiApplication.class, args);
        Receita r = new Receita();

    }

}
