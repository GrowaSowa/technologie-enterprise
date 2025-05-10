import { Routes } from '@angular/router';
import { StudentsComponent } from './students/students.component';
import { StudentDetailComponent } from './student-detail/student-detail.component';

export const routes: Routes = [
	{path: '', redirectTo: '/students', pathMatch: 'full'},
	{path: 'students', component: StudentsComponent},
	{path: 'student-detail/:id', component: StudentDetailComponent}
];
