import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AlertController} from "@ionic/angular";

@Component({
  selector: 'app-header',
  templateUrl: './header.page.html',
  styleUrls: ['./header.page.scss'],
})
export class HeaderPage implements OnInit {
  @Input() page?: string;

  constructor(private router: Router, private alertCtrl: AlertController) {
  }

  ngOnInit() {
  }

  public actionSheetButtons = [
    {
      text: 'Se deconnecter',
      role: close(),
    },
    {
      text: 'Annuler',
      role: 'cancel',
      data: {
        action: 'cancel',
      },
    },
  ];

  async presentConfirm() {
    let alert = await this.alertCtrl.create({
      header: 'Se déconnecter?',
      mode: 'ios',
      // message: 'Voulez-vous vous deconnecter?',
      cssClass: 'app-alert',
      backdropDismiss: false,
      buttons: [
        {
          text: 'Non',
          role: 'cancel'
        },
        {
          text: 'Oui',
          handler: () => {
            this.close()
          }
        }
      ]
    });
    await alert.present();
  }


  close() {
    localStorage.removeItem('email')
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    this.router.navigateByUrl('login');
  }

}
