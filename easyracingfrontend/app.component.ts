import { Component, Output } from '@angular/core';
import { EventService } from './services/event.service';
import { MatListModule } from '@angular/material/list';
import 'bootstrap';
import { RouterLink, Router } from '@angular/router';
import { UserService } from './services/user.service';
import { ComponentSterringService } from './services/component-sterring.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [RouterLink]
})
export class AppComponent {
  title = 'racing';

  isLogged: number = 0;
  roleNumber: number;


  constructor(
    private router: Router,
    private userService: UserService,
    private eventservice: EventService,
    private comp: ComponentSterringService

  ) { }

  ngOnInit(): void {

    this.comp.getRole().subscribe(data => {
      this.roleNumber = data;
    })

    this.comp.getLogin().subscribe(data => {
      this.isLogged = data;
    });


  }

  async getToEvents(e: number) {

    await this.router.navigate(['/events']);
    this.comp.eventChange(e);

  }

  async getToVechicles(e: number) {

    await this.router.navigate(['/vechicles']);
    this.comp.vechicleChange(e);


  }

  logout() {
    sessionStorage.clear();
    this.comp.login(0);
  }

  register() {
    this.comp.login(2);
  }



}

