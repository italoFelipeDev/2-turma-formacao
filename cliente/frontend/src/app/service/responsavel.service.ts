import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Table } from "primeng";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Page } from "../model/page";
import { Responsavel } from "../model/responsavel.model";
import { RequestUtil } from "../util/request-util";
import { DefaultFilter } from "./filter/default-filter";

@Injectable()
export class ResponsavelService{
    readonly resourceUrl = `${environment.apiUrl}/responsavel`;

    constructor(private httpClient: HttpClient) {}

    carregarResponsavel(): Observable<Responsavel[]> {
        return this.httpClient.get<Responsavel[]>(`${this.resourceUrl}`)
    }

    pesquisarResponsavel(defaultFilter : DefaultFilter, datatable: Table): Observable<Page<Responsavel>>{
        return this.httpClient.post<Page<Responsavel>>(`${this.resourceUrl}/pesquisar` , defaultFilter,
        {params: RequestUtil.getRequestParamsTable(datatable) });
    }
}
