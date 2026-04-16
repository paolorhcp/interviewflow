import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { JobApplicationRequest, JobApplicationResponse } from '../models/job-application.model';

@Injectable({
  providedIn: 'root'
})
export class JobApplicationService {
  private http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/applications`;

  getApplications(): Observable<JobApplicationResponse[]> {
    return this.http.get<JobApplicationResponse[]>(this.apiUrl);
  }

  createApplication(payload: JobApplicationRequest): Observable<JobApplicationResponse> {
    return this.http.post<JobApplicationResponse>(this.apiUrl, payload);
  }

  deleteApplication(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  updateApplication(id: number, payload: JobApplicationRequest): Observable<JobApplicationResponse> {
    return this.http.put<JobApplicationResponse>(`${this.apiUrl}/${id}`, payload);
  }
}
