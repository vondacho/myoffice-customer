import {NgModule} from '@angular/core';
import {CustomerRoutingModule} from './component/primeng/customer-routing.module';
import {CustomerClient} from './service/client/customer-client.service';

@NgModule({
    imports: [
        CustomerRoutingModule,
    ],
    providers: [
        CustomerClient
    ]
})
export class CustomerModule {
}
