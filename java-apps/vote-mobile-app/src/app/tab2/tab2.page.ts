import {Component} from '@angular/core';
import {LoadingController, ToastController} from '@ionic/angular';
import {ManagerService} from '../services/manager/manager.service';
import {Resultvote} from '../models/resultvote.model';
import {Chart} from "angular-highcharts";

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page {

  page = 'Home'
  results_vote: Resultvote[] = []
  chartNameV: any = [];
  chartNbreV: any = [];
  chartOptions: any;
  graphe: boolean = false;
  btnGraphe = 'graphe'
  activeTab: string = 'volume'

  constructor(
    private managerSrv: ManagerService,
    private loadingCtrl: LoadingController,
    private toastCtrl: ToastController
  ) {
    this.handlerGetResultVte()
  }


  async handlerGetResultVte() {
    const loading = await this.loadingCtrl.create({
      message: 'Patientez svp...',
      mode: 'ios',
      duration: 20000
    });
    await loading.present();
    this.managerSrv.onGetResultVote().subscribe({
      next: (result: Resultvote[]) => {
        result.forEach(item => {
          item.pourcentage = +item.pourcentage.toFixed(2)
        })
        this.results_vote = result;
        this.openGraphe(result)
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

  segmentChange($event: any) {
    if ($event.target.value === 'graph') {
      this.openGraphe(this.results_vote);
    }
    this.activeTab = $event.target.value;
  }


  barColors = ["red", "green", "blue", "orange", "brown"];


  openGraphe(data: Resultvote[]) {
    this.effacer()
    for (var i = 0; i < data.length; i++) {
      this.chartNameV.push(this.results_vote[i].nameCandidat);
      this.chartNbreV.push(this.results_vote[i].nbrVote);
    }
    const barColors = ['#6495ED', '#FFA07A', '#20B2AA', '#FFD700', '#FF6347'];

    this.chartOptions = new Chart({
      title: {
        text: 'Vue graphique'
      },
      xAxis: {
        categories: this.chartNameV,
      },
      yAxis: {
        title: {
          text: 'Statistique'
        }
      },
      colors: barColors,
      series: [{
        name: 'Nombre de votants',
        data: this.chartNbreV.map((value: any, index: any) => ({
          name: this.chartNameV[index],
          y: value,
          color: barColors[index % barColors.length], // modulo for cycling through colors
        })),
        type: 'pie',
      }],
    })
  }

  effacer() {
    this.chartNameV = [];
    this.chartNbreV = [];
  }

  onChangeVieuw() {
    this.graphe = !this.graphe
    if (this.graphe) {
      this.btnGraphe = 'Normal'
    } else {
      this.btnGraphe = 'graphe'
    }
  }
}
