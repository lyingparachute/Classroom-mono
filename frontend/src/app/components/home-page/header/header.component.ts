import {ChangeDetectionStrategy, Component, input} from '@angular/core';
import {CommonModule} from '@angular/common';

@Component({
    selector: 'lp-header',
    standalone: true,
    imports: [CommonModule],
    template: `
    <header id="header" class="fixed-top">
        <div class="container d-flex align-items-center justify-content-between">
            <h1 class="logo"><a href="" th:text="#{navbar.app.name}"></a></h1>
            <nav id="navbar" class="navbar">
                <ul>
                    @for (navLink of navLinks(); track navLink) {
                        <li><a class="getstarted" href="{{navLink}}">{{ navLink }} </a></li>
                    }
                </ul>
                <i class="fas fa-bars mobile-nav-toggle"></i>
            </nav>
        </div>
    </header>
    `,
    styles: [``],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HeaderComponent {
    navLinks = input.required<string[]>();
}
