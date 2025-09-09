import {RouterModule} from "@angular/router";
import {MenuComponent} from "./menu/menu.component";
import {Component} from "@angular/core";

@Component({
  selector: 'app-shell',
  standalone: true,
  imports: [RouterModule, MenuComponent],
  template: `
    <app-menu />
    <router-outlet />
  `
})
export class ShellComponent {}
