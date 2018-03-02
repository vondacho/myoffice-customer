import {NgModule} from '@angular/core';
import {CustomerEditComponent} from './customer-edit/customer-edit.component';
import {FolderEditComponent} from './folder-edit/folder-edit.component';
import {FolderListComponent} from './folder-list/folder-list.component';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {CustomerSearchComponent} from './customer-search/customer-search.component';
import {FolderSearchComponent} from './folder-search/folder-search.component';
import {CommonModule} from '@angular/common';
import {CalendarModule, ContextMenuModule, DataTableModule, GrowlModule, PaginatorModule} from 'primeng/primeng';

@NgModule({
    imports: [
        CommonModule,
        CalendarModule,
        DataTableModule,
        PaginatorModule,
        ContextMenuModule,
        GrowlModule
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
