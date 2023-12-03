import {Component, OnInit} from '@angular/core';
import {LoadingController} from '@ionic/angular';
import {Stat} from 'src/app/models/stat.model';
import {ManagerService} from 'src/app/services/manager/manager.service';
import {register} from 'swiper/element/bundle';

register();

@Component({
  selector: 'app-swiper',
  templateUrl: './swiper.page.html',
  styleUrls: ['./swiper.page.scss'],
})
export class SwiperPage implements OnInit {
  public stats!: Stat;

  constructor(private managerSrv: ManagerService, private loadingCtrl: LoadingController) {
  }

  ngOnInit() {
    this.handleGetStats()
  }

  async handleGetStats() {
    const loading = await this.loadingCtrl.create({
      message: 'Patientez svp...',
      mode: 'ios',
      duration: 20000
    });
    await loading.present();

    this.managerSrv.onGetStat().subscribe({
      next: (stats: Stat) => {
        this.stats = stats;
        console.log(stats);

        loading.dismiss()
      }, error: err => {
        console.log(err)
        loading.dismiss()
      }
    })
  }

  refress() {
    this.handleGetStats();
  }
}
