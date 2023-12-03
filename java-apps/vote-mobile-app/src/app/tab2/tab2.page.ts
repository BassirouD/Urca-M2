import { Component } from '@angular/core';
import { LoadingController, ToastController } from '@ionic/angular';
import { ManagerService } from '../services/manager/manager.service';
import { Resultvote } from '../models/resultvote.model';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page {

  page = 'Home'
  results_vote:Resultvote[]=[]

  constructor(
    private managerSrv: ManagerService,
    private loadingCtrl: LoadingController,
    private toastCtrl: ToastController
    ) {
      this.handlerGetResultVte()
    }


  async handlerGetResultVte(){
    const loading = await this.loadingCtrl.create({
      message: 'Patientez svp...',
      mode: 'ios',
      duration: 20000
    });
    await loading.present();
    this.managerSrv.onGetResultVote().subscribe({
      next: (result: Resultvote[]) => {
        result.forEach(item =>{
          item.pourcentage = +item.pourcentage.toFixed(2)
        })
        this.results_vote = result;
        loading.dismiss()
      }, error: err => {
        console.log(err)
        loading.dismiss()
      }
    })
  }

  handleRefresh($event: any) {
    setTimeout(() => {
      this.handlerGetResultVte();
      $event.target.complete();
    }, 2000);
  }
}
