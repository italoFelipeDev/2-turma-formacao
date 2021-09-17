import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Table } from "primeng";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Page } from "../model/page";
import { Tarefa } from "../model/tarefa.model";
import { RequestUtil } from "../util/request-util";
import { DefaultFilter } from "./filter/default-filter";

@Injectable()
export class TarefaService{
    readonly resourceUrl = `${environment.apiUrl}/tarefa`;

    constructor(private httpClient: HttpClient) {}

    carregarTarefa(): Observable<Tarefa[]> {
        return this.httpClient.get<Tarefa[]>(`${this.resourceUrl}`)
    }

    pesquisarTarefa(defaultFilter : DefaultFilter, datatable: Table): Observable<Page<Tarefa>>{
        return this.httpClient.post<Page<Tarefa>>(`${this.resourceUrl}/pesquisar` , defaultFilter,
        {params: RequestUtil.getRequestParamsTable(datatable) });
    }
}
