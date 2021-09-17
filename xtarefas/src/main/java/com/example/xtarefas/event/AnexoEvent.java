package com.example.xtarefas.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AnexoEvent extends DefaultEvent {

    public AnexoEvent(Long id){
        super(id);
    }
}
