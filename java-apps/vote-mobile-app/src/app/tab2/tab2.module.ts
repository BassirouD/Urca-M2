import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Tab2Page } from './tab2.page';
import { ExploreContainerComponentModule } from '../explore-container/explore-container.module';

import { Tab2PageRoutingModule } from './tab2-routing.module';
import {HeaderPageModule} from "../pages/header/header.module";
import { SwiperPageModule } from '../pages/swiper/swiper.module';

@NgModule({
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        ExploreContainerComponentModule,
        Tab2PageRoutingModule,
        HeaderPageModule,
        SwiperPageModule
    ],
  declarations: [Tab2Page]
})
export class Tab2PageModule {}
