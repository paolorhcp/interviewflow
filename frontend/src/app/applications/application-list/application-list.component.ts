import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { JobApplicationService } from '../../core/services/job-application.service';
import { JobApplicationResponse } from '../../core/models/job-application.model';

@Component({
  selector: 'app-application-list',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './application-list.component.html'
})
export class ApplicationListComponent implements OnInit {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private jobApplicationService = inject(JobApplicationService);
  private router = inject(Router);

  applications: JobApplicationResponse[] = [];
  errorMessage = '';
  isSubmitting = false;
  editingId: number | null = null;

  applicationForm = this.fb.group({
    company: ['', [Validators.required]],
    position: ['', [Validators.required]],
    status: ['APPLIED', [Validators.required]]
  });

  ngOnInit(): void {
    this.loadApplications();
  }

  loadApplications(): void {
    this.jobApplicationService.getApplications().subscribe({
      next: (data) => {
        this.applications = data;
      },
      error: (error) => {
        this.errorMessage = error?.error?.message || 'Errore nel caricamento candidature';
      }
    });
  }

  onSubmit(): void {
    if (this.applicationForm.invalid) {
      this.applicationForm.markAllAsTouched();
      return;
    }

    this.errorMessage = '';
    this.isSubmitting = true;

    const payload = this.applicationForm.getRawValue() as {
      company: string;
      position: string;
      status: string;
    };

    if (this.editingId) {
      this.jobApplicationService.updateApplication(this.editingId, payload).subscribe({
        next: () => {
          this.resetForm();
          this.loadApplications();
        },
        error: (error) => {
          this.isSubmitting = false;
          this.errorMessage = error?.error?.message || 'Errore aggiornamento';
        }
      });
    } else {
      this.jobApplicationService.createApplication(payload).subscribe({
        next: () => {
          this.resetForm();
          this.loadApplications();
        },
        error: (error) => {
          this.isSubmitting = false;
          this.errorMessage = error?.error?.message || 'Errore creazione';
        }
      });
    }
  }

  editApplication(app: JobApplicationResponse): void {
    this.editingId = app.id;

    this.applicationForm.setValue({
      company: app.company,
      position: app.position,
      status: app.status
    });
  }

  deleteApplication(id: number): void {
    this.jobApplicationService.deleteApplication(id).subscribe({
      next: () => {
        this.applications = this.applications.filter((application) => application.id !== id);

        if (this.editingId === id) {
          this.resetForm();
        }
      },
      error: (error) => {
        this.errorMessage = error?.error?.message || 'Errore nella cancellazione candidatura';
      }
    });
  }

  resetForm(): void {
    this.isSubmitting = false;
    this.editingId = null;

    this.applicationForm.reset({
      company: '',
      position: '',
      status: 'APPLIED'
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
