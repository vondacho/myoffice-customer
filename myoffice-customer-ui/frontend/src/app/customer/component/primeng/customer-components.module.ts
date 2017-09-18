import { NgModule } from '@angular/core';
import {CalendarModule} from 'primeng/primeng';
import {CustomerEditComponent} from './customer-edit/customer-edit.component';
import {FolderEditComponent} from './folder-edit/folder-edit.component';
import { FolderListComponent } from './folder-list/folder-list.component';
import { CustomerListComponent } from './customer-list/customer-list.component';

@NgModule({
  imports: [
    CalendarModule
  ],
  declarations: [
    CustomerEditComponent,
    FolderEditComponent,
    FolderListComponent,
    CustomerListComponent,
  ],
  exports: [
    CustomerEditComponent,
    FolderEditComponent,
  ]
})
export class CustomerComponentsModule { }
