import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoadingController} from "@ionic/angular";
import {AuthService} from "../../../services/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {
  type: boolean = true;
  form!: FormGroup

  constructor(private loadingCtrl: LoadingController,
              private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      email: new FormControl('', {validators: [Validators.required]}),
      password: new FormControl('', {
        validators: [Validators.required]
      })
    })
  }

  async handlerRegister() {
    const loading = await this.loadingCtrl.create({
      message: 'Patientez svp...',
      mode: 'ios',
      duration: 10000
    });
    await loading.present();
    this.authService.register(this.form.value).subscribe({
      next: result => {
        loading.dismiss();
        this.router.navigateByUrl('/login');
      }, error: err => {
        console.log(err)
        loading.dismiss();
      }
    })
  }

}
