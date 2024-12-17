import { Component, OnInit } from '@angular/core';
import { ApiVideosService } from '../../services/api-videos.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-show-video',
  standalone: true,
  imports: [],
  templateUrl: './show-video.component.html',
  styleUrl: './show-video.component.css'
})
export class ShowVideoComponent implements OnInit{
  id: string = '';
  constructor(private apiVideo: ApiVideosService, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      document.getElementById('video')!.setAttribute('src', `http://localhost:5206/api/Video/download/${this.id}`);
    });
  }
}
