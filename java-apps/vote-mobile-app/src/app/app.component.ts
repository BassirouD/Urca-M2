import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  constructor(private router: Router) {
    if (localStorage.getItem('token')) {
      this.router.navigateByUrl('/tabs/tab1');
    } else {
      this.router.navigateByUrl('/login');
    }
  }
}
