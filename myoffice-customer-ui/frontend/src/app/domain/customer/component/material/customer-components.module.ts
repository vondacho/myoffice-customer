import {NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FolderEditComponent} from './folder-edit/folder-edit.component';
import {CustomerEditComponent} from './customer-edit/customer-edit.component';

@NgModule({
  imports: [
    BrowserAnimationsModule,
/*    MdFormFieldModule,
    MdDatepickerModule,
    MdNativeDateModule,
    MdInputModule,*/
  ],
  declarations: [
    CustomerEditComponent,
    FolderEditComponent,
  ]
})
export class CustomerComponentsModule { }
