export interface JobApplicationRequest {
  company: string;
  position: string;
  status: string;
  applicationDate?: string;
}

export interface JobApplicationResponse {
  id: number;
  company: string;
  position: string;
  status: string;
  applicationDate: string;
}
