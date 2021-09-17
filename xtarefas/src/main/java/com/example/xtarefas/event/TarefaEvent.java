package com.example.xtarefas.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TarefaEvent extends DefaultEvent {

    public TarefaEvent(Long id){
        super(id);
    }
}
