import {ChangeDetectionStrategy, Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HeaderComponent} from "./header/header.component";

@Component({
    selector: 'lp-home-page',
    standalone: true,
    imports: [CommonModule, HeaderComponent],
    template: `<lp-header [navLinks]="navLinks"></lp-header>
    `,
    styles: [``],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HomePageComponent {
    navLinks: string[] = ['Home', 'About', 'Services', 'Pricing', 'Dashboard', 'Sign-up'];
}
