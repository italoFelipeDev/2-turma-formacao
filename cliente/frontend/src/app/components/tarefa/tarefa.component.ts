import { Component, OnInit } from '@angular/core';
import { Table } from 'primeng';
import { Page } from 'src/app/model/page';
import { Tarefa } from 'src/app/model/tarefa.model';
import { DefaultFilter } from 'src/app/service/filter/default-filter';
import { TarefaService } from 'src/app/service/tarefa.service';

@Component({
  selector: 'app-tarefa',
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css']
})
export class TarefaComponent implements OnInit {

  dataTable:Table;

  tarefas: Page<Tarefa> = new Page<Tarefa>();
  
  tarefaFiltro = new DefaultFilter();
  
  constructor(
    private tarefaService: TarefaService    
  ) { }

  ngOnInit(): void {
    this.pesquisarTarefa();
  }


  pesquisarTarefa(){
    this.tarefaService.pesquisarTarefa(this.tarefaFiltro, this.dataTable).subscribe(
      res => {
        this.tarefas = res;
      }
    );
  }
}
