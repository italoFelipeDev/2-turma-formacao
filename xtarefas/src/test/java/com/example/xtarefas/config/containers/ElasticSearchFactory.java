package com.example.xtarefas.config.containers;

import java.util.Objects;

public class ElasticSearchFactory {

    private static CustomElasticContainer container;

    public static CustomElasticContainer getInstance(){
        if(Objects.isNull(container)){
            container = new CustomElasticContainer();
            container.start();
        }
        return container;
    }
}
