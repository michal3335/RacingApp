import { Component, OnInit, Input } from '@angular/core';
import { EventService } from '../services/event.service';
import { Event } from '../models/event';
import { Observable } from 'rxjs/internal/Observable';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ComponentSterringService } from '../services/component-sterring.service';
import { Competition } from '../models/competition';
import { Url } from 'url';
import { OrganiserService } from '../services/organiser.service';
import { DriverService } from '../services/driver.service';
import { Driver } from '../models/driver';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Result } from '../models/result';
@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  track: File;
  logo: File;
  events: Observable<Event[]>;
  OrganiserEvents: Observable<Event[]>;
  DriverEvents: Observable<Event[]>;
  competitions: Observable<Competition[]>;
  competitionDrivers: Observable<Driver[]>;
  results: Observable<Result[]>;
  id_account: string;
  choosen;
  event_id: number;
  comp_id: number;
  clicked: number;
  blob: Blob;
  url: string | ArrayBuffer;
  src: SafeUrl;
  isImageLoading: boolean;
  role: string;

  divChange: number = 1;

  constructor(private eventService: EventService,
              private sanitizer: DomSanitizer,
              private comp: ComponentSterringService,
              private organiserService: OrganiserService,
              private driverService: DriverService) { }

  ngOnInit() {

    this.comp.getEventSum().subscribe(data => {
      this.divChange = data;
    });

    this.role = sessionStorage.getItem('role');

    if (this.role === "organiser") {
      this.getOrganiserEvents();
      this.getDriverEvents();
      this.getEvents();
    } else if (this.role === "driver") {
      this.getDriverEvents();
      this.getEvents();
    }else{
      this.getEvents();
    }
    this.id_account = sessionStorage.getItem('id');
  }

  onFileChanged(Eventt) {

    if (Eventt.target.files && Eventt.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(Eventt.target.files[0]);

      reader.onload = (event) => {
        this.url = event.target.result;
      }
      this.track = Eventt.target.files[0];
    }
  }

  onClickSubmit(data) {
    let event: Event = {};
    event.name = data.name;
    event.localization = data.localization;
    event.date = data.date;
    event.description = data.description;
    this.eventService.addEvent(this.track, event, sessionStorage.getItem("email")).subscribe(res => {
      this.comp.eventChange(4);
      this.url = null;
    });
  }

  getEvents() {
    this.events = this.eventService.getEvents();
  }

  getOrganiserEvents() {

    var output = parseInt(sessionStorage.getItem("id"), 10);
    this.OrganiserEvents = this.organiserService.getOrganiserEvents(output);
  }

  getResults(id: number) {

    return this.eventService.getResults(id);
  }

  showResults(id: number){

    this.comp_id = id;
    this.results = this.getResults(id);
    this.divChange = 7;
  }

  getCompetitions(id: number) {

    return this.eventService.getCompetitions(id);
  }

  getCompetitionDrivers(id: number) {
    return this.eventService.getCompetitionDrivers(id);
  }

  showCompetitionDrivers(id: number) {
    this.competitionDrivers = this.getCompetitionDrivers(id);
  }

  getDriverEvents() {

    var output = parseInt(sessionStorage.getItem("id"), 10);

    this.DriverEvents = this.driverService.getDriverEvents(output);
  }

  onClickSubmitCompetition(data) {

    this.eventService.addCompetition(this.event_id, data.name, data.rules, data.distance).subscribe(res => {
      this.competitions = this.getCompetitions(this.event_id);
    })
  }

  onClickSubmitResult(data) {

    this.eventService.addResult(this.comp_id, data.name, data.surname, data.time, data.score).subscribe(res => {
      this.results = this.getResults(this.comp_id);
    })
  }

  deleteEvent(id: number) {
    this.eventService.deleteEvent(id).subscribe(res => {
      this.getOrganiserEvents();
      this.divChange = 4;
    });

  }

  deleteResult(id: number) {
    this.eventService.deleteResult(id).subscribe(res => {
      this.results = this.getResults(this.comp_id);
    });

  }

  addCompetition(id: number) {
    this.event_id = id;
    this.competitions = this.getCompetitions(id);
    this.divChange = 6;

  }

  deleteCompetition(id: number) {
    this.eventService.deleteCompetition(id).subscribe(res => {
      this.addCompetition(this.event_id);
    });
  }

  checkDriver(id: number) {

  }

  join(id: number) {
    this.driverService.join(id, sessionStorage.getItem('email')).subscribe(res => {
      console.log("dołączyłeś");
      this.competitionDrivers = this.getCompetitionDrivers(id);
    });
  }

  cancelJoin(id: number) {
    this.driverService.cancelJoin(id, this.id_account).subscribe(res => {
      console.log("już nie należysz do wyścigu");
      this.competitionDrivers = this.getCompetitionDrivers(id);
    });

  }

  addNew() {
    this.divChange = 3;
  }

  checkEvent(id: number) {
    this.clicked = id;
    this.competitions = this.getCompetitions(id);
    this.eventService.getEvent(id).subscribe(pom => {
      this.choosen = pom;
    }
    );
    this.getImageFromService(id);
    this.divChange = 2;
  }

  getImageFromService(id: number) {

    this.eventService.getImage(id).subscribe(data => {
      this.createImageFromBlob(data);
    }, error => {
      console.log(error);
    }
    );
  }

  async createImageFromBlob(image: Blob) {
    let url;
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      let a: any = reader.result;
      this.src = this.sanitizer.bypassSecurityTrustUrl(a);
    }, false);

    reader.addEventListener("onerror", function (error) {
      console.log("error" + error);
    }, false);

    if (image) {
      reader.readAsDataURL(image);

    }


  }

}
