package com.unoeste.fallpreventionbe;

import com.unoeste.fallpreventionbe.entities.Configuracao;
import com.unoeste.fallpreventionbe.services.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FallPreventionBeApplication {


    public static void main(String[] args)
    {

        SpringApplication.run(FallPreventionBeApplication.class, args);
    }
    @Bean
    ApplicationRunner atualizarIPv4Local(ConfiguracaoService configuracaoService)
    {
        return args -> {
            System.out.println(configuracaoService.AtualizarIPv4Local().getIp());

        };
    }


}
