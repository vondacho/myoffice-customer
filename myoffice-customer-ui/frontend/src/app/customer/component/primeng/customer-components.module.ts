import {NgModule} from '@angular/core';
import {CalendarModule} from 'primeng/primeng';
import {CustomerEditComponent} from './customer-edit/customer-edit.component';
import {FolderEditComponent} from './folder-edit/folder-edit.component';
import {FolderListComponent} from './folder-list/folder-list.component';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {CustomerSearchComponent} from './customer-search/customer-search.component';
import {FolderSearchComponent} from './folder-search/folder-search.component';

@NgModule({
    imports: [
        CalendarModule
    ],
    declarations: [
        CustomerEditComponent,
        CustomerListComponent,
        CustomerSearchComponent,
        FolderEditComponent,
        FolderListComponent,
        FolderSearchComponent
    ],
    exports: [
        CustomerEditComponent,
        FolderEditComponent,
    ]
})
export class CustomerComponentsModule {
}
