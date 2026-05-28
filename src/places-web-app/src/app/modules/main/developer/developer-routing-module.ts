import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ButtonsComponent } from './pages/buttons/buttons.component';
import { AutocompleteExampleComponent } from './pages/autocomplete-example/autocomplete-example.component';
import { DialogsExampleComponent } from './pages/dialogs-example/dialogs-example.component';
import { SearchFormComponent } from './pages/search-form/search-form.component';
import { TableExampleComponent } from './pages/table-example/table-example.component';

const routes: Routes = [
  {
      path: 'buttons',
      component: ButtonsComponent
    },
    {
      path: 'autocomplete',
      component: AutocompleteExampleComponent
    },
    {
      path: 'dialogs',
      component: DialogsExampleComponent
    },
    {
      path: 'search-form',
      component: SearchFormComponent
    },
    {
      path: 'table-example',
      component: TableExampleComponent
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DeveloperRoutingModule {}
