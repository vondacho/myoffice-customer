import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CustomerComponentsModule} from './customer-components.module';
import {FolderListComponent} from './folder-list/folder-list.component';
import {CustomerListComponent} from './customer-list/customer-list.component';

const routes: Routes = [
    {
        path: 'customer',
        component: CustomerListComponent
    },
    {
        path: 'folder',
        component: FolderListComponent
    }
];

@NgModule({
    imports: [
        CustomerComponentsModule,
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class CustomerRoutingModule {
}
