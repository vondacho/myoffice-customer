import {NgModule} from '@angular/core';
import {CustomerRoutingModule} from './component/primeng/customer-routing.module';
import {CustomerClient} from './service/client/customer-client.service';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
    imports: [
        CustomerRoutingModule,
        HttpClientModule
    ],
    providers: [
        CustomerClient
    ]
})
export class CustomerModule {
}
