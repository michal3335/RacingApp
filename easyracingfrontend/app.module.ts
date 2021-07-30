import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { EventService} from './services/event.service';
import {MatListModule} from '@angular/material/list';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MatToolbarModule, } from  '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AppRoutingModule, routingComponents } from '../app/app-routing/app-routing.module';
import { RegisterComponent } from './register/register.component';
import { VechicleComponent } from './vechicle/vechicle.component';
import {MatSelectModule} from '@angular/material/select';
import 'bootstrap';
import { ComponentSterringService } from './services/component-sterring.service';



@NgModule({
  declarations: [
    AppComponent,
   routingComponents,
   RegisterComponent,
   VechicleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatListModule,
    MatToolbarModule,
    MatSidenavModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatSelectModule
  ],
  providers: [EventService, ComponentSterringService],
  bootstrap: [AppComponent]
})
export class AppModule { }
