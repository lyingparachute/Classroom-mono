import {Component} from '@angular/core';
import {RouterModule} from '@angular/router';
import {NxWelcomeComponent} from './nx-welcome.component';
import {HomePageComponent} from "./components/home-page/home-page.component";

@Component({
  standalone: true,
    imports: [NxWelcomeComponent, RouterModule, HomePageComponent],
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'frontend';
}
