import { Component, inject, OnInit } from '@angular/core';
import { ApiVideosService } from '../../services/api-videos.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-videos',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './videos.component.html',
  styleUrl: './videos.component.css'
})
export class VideosComponent implements OnInit{
  private apiService: ApiVideosService = inject(ApiVideosService)
  data: any[] = [];

  ngOnInit(): void {
    this.apiService.getData('Video').subscribe({
      next: (response) => {
        response.sort((a: any, b: any) => {
          return parseInt(b.id!) - parseInt(a.id!);
        });
        this.data = response;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  download(id: string, name: string): void {
    this.apiService.download('Video', id).subscribe({
      next: (blob) => {
        console.log(blob);
        const a = document.createElement('a');
        const objectUrl = URL.createObjectURL(blob);
        a.href = objectUrl;
        a.download = name;
        a.click();
        URL.revokeObjectURL(objectUrl);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }
}
