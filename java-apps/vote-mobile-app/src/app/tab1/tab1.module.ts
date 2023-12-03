import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Tab1Page } from './tab1.page';
import { ExploreContainerComponentModule } from '../explore-container/explore-container.module';

import { Tab1PageRoutingModule } from './tab1-routing.module';
import {HeaderPageModule} from "../pages/header/header.module";
import { SwiperPageModule } from '../pages/swiper/swiper.module';

@NgModule({
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        ExploreContainerComponentModule,
        Tab1PageRoutingModule,
        HeaderPageModule,
        SwiperPageModule
    ],
  declarations: [Tab1Page]
})
export class Tab1PageModule {}
