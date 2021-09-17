import { Component, OnInit } from '@angular/core';
import { Table } from 'primeng';
import { Page } from 'src/app/model/page';
import { Responsavel } from 'src/app/model/responsavel.model';
import { DefaultFilter } from 'src/app/service/filter/default-filter';
import { ResponsavelService } from 'src/app/service/responsavel.service';

@Component({
  selector: 'app-responsavel',
  templateUrl: './responsavel.component.html',
  styleUrls: ['./responsavel.component.css']
})
export class ResponsavelComponent implements OnInit {

  dataTable:Table;

  responsaveis:Page<Responsavel> = new Page<Responsavel>();
  
  responsavelFiltro = new DefaultFilter();

  

  constructor(
    private responsavelService: ResponsavelService
     ) { }

  ngOnInit(): void {
    this.pesquisarResponsavel();
  }

  pesquisarResponsavel(){
    this.responsavelService.pesquisarResponsavel(this.responsavelFiltro, this.dataTable).subscribe(
      res => {
        this.responsaveis= res;
      }
    );
  }
  
}
