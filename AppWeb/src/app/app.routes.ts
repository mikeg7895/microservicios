import { Routes } from '@angular/router';
import { LogsComponent } from './pages/logs/logs.component';
import { VideosComponent } from './pages/videos/videos.component';
import { HomeComponent } from './pages/home/home.component';
import { ShowVideoComponent } from './pages/show-video/show-video.component';

export const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'logs', component: LogsComponent},
  {path: 'videos', component: VideosComponent},
  {path: 'show-video/:id', component: ShowVideoComponent}
];
