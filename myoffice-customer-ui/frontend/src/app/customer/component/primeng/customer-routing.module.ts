import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CustomerEditComponent} from './customer-edit/customer-edit.component';
import {FolderEditComponent} from './folder-edit/folder-edit.component';
import {CustomerComponentsModule} from './customer-components.module';

const routes: Routes = [
  {
    path: 'customer',
    component: CustomerEditComponent
  },
  {
    path: 'folder',
    component: FolderEditComponent
  }
];

@NgModule({
  imports: [
    CustomerComponentsModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule]
})
export class CustomerRoutingModule {
}
