import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/auth/auth.service";
import {LoadingController} from "@ionic/angular";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  type: boolean = true;
  form!: FormGroup

  constructor(private router: Router,
              private loadingCtrl: LoadingController,
              private authService: AuthService
  ) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      email: new FormControl('bassirou.diallo@gmail.com', {validators: [Validators.required]}),
      password: new FormControl('toor98', {
        validators: [Validators.required]
      })
    })
  }

  async handlerLogin() {
    const loading = await this.loadingCtrl.create({
      message: 'Patientez svp...',
      mode: 'ios',
      duration: 10000
    });
    await loading.present();
    this.authService.login(this.form.value).subscribe({
      next: result => {
        console.log(result)
        // @ts-ignore
        localStorage.setItem('email', result['email'])
        loading.dismiss();
        this.router.navigateByUrl('/tabs/tab1');
      }, error: err => {
        console.log(err)
        loading.dismiss();
      }
    })
  }

}
