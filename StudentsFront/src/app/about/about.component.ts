import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './about.component.html',
  styleUrl: './about.component.css'
})
export class AboutComponent {
	date = Date.now();
}
