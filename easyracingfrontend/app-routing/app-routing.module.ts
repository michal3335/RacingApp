import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { EventsComponent } from '../events/events.component';
import { RegisterComponent } from '../register/register.component';
import { VechicleComponent } from '../vechicle/vechicle.component';


const routes: Routes = [
    {path: 'login', component: LoginComponent},
    {path: 'events', component: EventsComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'vechicles', component: VechicleComponent}
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ],
    declarations: []
})

export class AppRoutingModule { }
export const routingComponents = [EventsComponent, LoginComponent, RegisterComponent, VechicleComponent];
