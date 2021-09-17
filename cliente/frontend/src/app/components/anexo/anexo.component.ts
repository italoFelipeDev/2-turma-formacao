import { Component, OnInit } from '@angular/core';
import { Table } from 'primeng';
import { Anexo } from 'src/app/model/anexo.model';
import { Page } from 'src/app/model/page';
import { AnexoService } from 'src/app/service/anexo.service';
import { DefaultFilter } from 'src/app/service/filter/default-filter';

@Component({
  selector: 'app-anexo',
  templateUrl: './anexo.component.html',
  styleUrls: ['./anexo.component.css']
})
export class AnexoComponent implements OnInit {

  dataTable:Table;
  
  anexos: Page<Anexo> = new Page<Anexo>();

  anexoFiltro = new DefaultFilter();

  constructor(
    private anexoService :AnexoService
  ) {}

  ngOnInit(): void {
    this.pesquisarAnexo();
  }

  pesquisarAnexo(){
    this.anexoService.pesquisarAnexo(this.anexoFiltro, this.dataTable).subscribe(
      res => {
        this.anexos = res;
      }
    );
  }

}
