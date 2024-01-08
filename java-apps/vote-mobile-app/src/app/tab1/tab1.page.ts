import {Component} from '@angular/core';
import {Candidat} from "../models/candidat.model";
import {ManagerService} from "../services/manager/manager.service";
import {LoadingController, ToastController} from "@ionic/angular";
import {Vote} from "../models/vote.model";

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page {

  page = 'Home'
  candidats: Candidat[] = [];
  alreadyDone: boolean = false;

  constructor(private managerSrv: ManagerService,
              private loadingCtrl: LoadingController,
              private toastCtrl: ToastController
  ) {
    this.handleGetAllCandidat();
  }

  async handleGetAllCandidat() {
    const loading = await this.loadingCtrl.create({
      message: 'Patientez svp...',
      mode: 'ios',
      duration: 20000
    });
    await loading.present();

    this.managerSrv.onGetAllCandidat().subscribe({
      next: (candidats: Candidat[]) => {
        this.candidats = candidats;
        loading.dismiss()
      }, error: err => {
        console.log(err)
        loading.dismiss()
      }
    })
  }

  async handlerVoter(candidateId: String) {
    let userEmail = localStorage.getItem('email')
    // @ts-ignore
    let check = JSON.parse(localStorage.getItem('user'))
    console.log(check)
    if (check) {
      let vote = check['vote']
      let email = check['email']
      console.log(vote, '---', email)
      if (vote == 'true' && email == userEmail)
        this.presentToast('Vous avez déja voté', 'warning')
    } else {
      const loading = await this.loadingCtrl.create({
        message: 'Patientez svp...',
        mode: 'ios',
        duration: 10000
      });
      await loading.present();
      // @ts-ignore
      const vote: Vote = {'userEmail': userEmail, 'candidateId': candidateId}
      this.managerSrv.onGetVoter(vote).subscribe({
        next: vote => {
          loading.dismiss()
          const alreadyDone = {'email': userEmail, vote: 'true'}

          localStorage.setItem('user', JSON.stringify(alreadyDone))
          this.presentToast('Vous avez voté avec success', 'success')
        }, error: err => {
          loading.dismiss();
          this.presentToast('Une erreur est survenue', 'danger')
        }
      })
    }
  }

  async presentToast(message: string, color: string) {
    const toast = await this.toastCtrl.create({
      message: message,
      duration: 4000,
      position: "bottom",
      color: color
    })
    await toast.present()
  }

  handleRefresh($event: any) {
    setTimeout(() => {
      this.handleGetAllCandidat();
      $event.target.complete();
    }, 2000);
  }

}
