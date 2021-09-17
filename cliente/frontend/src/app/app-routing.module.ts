import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { ResponsavelComponent } from './components/responsavel/responsavel.component';
import { TarefaComponent } from './components/tarefa/tarefa.component';
import { AnexoComponent } from './components/anexo/anexo.component';

const routes: Routes = [
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    { path: 'responsavel', component: ResponsavelComponent},
    { path: 'tarefa', component: TarefaComponent},
    { path: 'anexo', component: AnexoComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
