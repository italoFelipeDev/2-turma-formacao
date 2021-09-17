package com.example.xtarefas.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResponsavelEvent extends  DefaultEvent{

    public ResponsavelEvent(Long id){
        super(id);
    }
}
