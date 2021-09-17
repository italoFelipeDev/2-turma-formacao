import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Table } from "primeng";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Anexo } from "../model/anexo.model";
import { Page } from "../model/page";
import { RequestUtil } from "../util/request-util";
import { DefaultFilter } from "./filter/default-filter";

@Injectable()
export class AnexoService{
    readonly resourceUrl = `${environment.apiUrl}/anexo`;

    constructor(private httpClient: HttpClient) {}

    carregarAnexo(): Observable<Anexo[]> {
        return this.httpClient.get<Anexo[]>(`${this.resourceUrl}`)
    }

    pesquisarAnexo(defaultFilter : DefaultFilter, datatable: Table): Observable<Page<Anexo>>{
        return this.httpClient.post<Page<Anexo>>(`${this.resourceUrl}/pesquisar` , defaultFilter,
        {params: RequestUtil.getRequestParamsTable(datatable) });
    }
}