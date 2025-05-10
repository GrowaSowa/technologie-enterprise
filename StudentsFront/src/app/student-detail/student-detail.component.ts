import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location, UpperCasePipe } from '@angular/common';
import { Student } from '../student';
import { StudentService } from '../student.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-student-detail',
  standalone: true,
  imports: [FormsModule, UpperCasePipe],
  templateUrl: './student-detail.component.html',
  styleUrl: './student-detail.component.css'
})
export class StudentDetailComponent {
	student?: Student;

	constructor(
		private route: ActivatedRoute,
		private studentService: StudentService,
		private location: Location
	) {}

	ngOnInit() {
		this.getStudent();
	}

	getStudent(): void {
		const pathId = this.route.snapshot.paramMap.get('id');
		if (pathId) {
			this.studentService.getStudent(+pathId)
				.subscribe(student => this.student = student);
		}
	}

	save(): void {
		this.studentService.updateStudent(this.student!)
			.subscribe(() => this.goBack());
	}

	goBack(): void {
		this.location.back();
	}
}
