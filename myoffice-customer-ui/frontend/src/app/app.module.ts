import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CustomerModule} from './customer/customer.module';
import {CoreModule} from './core/core.module';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {DirtyRequestBodyInterceptor} from './core/util/interceptor/dirty-request-body-interceptor';

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        CustomerModule,
        CoreModule
    ],
    providers: [
        {provide: HTTP_INTERCEPTORS, useClass: DirtyRequestBodyInterceptor, multi: true},
//      { provide: APP_BASE_HREF, useValue: '/ui' }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
