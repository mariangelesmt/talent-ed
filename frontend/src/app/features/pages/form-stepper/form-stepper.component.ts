import { Component } from '@angular/core';
import { FormService } from '../../../core/services/form.service';
import { FormQuestion } from '../../../core/models/formQuestion.model';
import { StepsModule } from 'primeng/steps';
import { MenuItem } from 'primeng/api';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ResponseService } from '../../../core/services/response.service';
import { ApiAiService } from '../../../core/services/apiAi.service';
import { ProgressBarModule } from 'primeng/progressbar';

interface AnswerDTO {
  questionId: number;
  answerText: string;
}

interface ResponseDTO {
  form: any;
  submittedAt: Date;
  answers: AnswerDTO[];
}

@Component({
  selector: 'app-form-stepper',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    StepsModule,
    ButtonModule,
    InputTextModule,
    ProgressBarModule,
  ],
  templateUrl: './form-stepper.component.html',
  styleUrl: './form-stepper.component.scss',
})
export class FormStepperComponent {
  steps: MenuItem[] = [];
  questions: FormQuestion[] = [];
  answers: { [key: number]: any } = {};
  form: any | null = null;
  activeIndex = 0;
  isLoading = true;

  constructor(
    private formService: FormService,
    private route: ActivatedRoute,
    private router: Router,
    private responseService: ResponseService,
    private apiAiService: ApiAiService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const offerId = params['id'];
      this.formService.getFormByOfferId(offerId).subscribe((data: any) => {
        this.form = data;
        this.questions = data.questions ?? [];
        this.steps = this.questions.map((q, index) => ({
          label: `Pregunta ${index + 1}`,
        }));
        this.isLoading = false;
      });
    });
  }

  get progressValue() {
    return this.questions.length > 0 ? ((this.activeIndex + 1) / this.questions.length) * 100 : 0;
  }

  next(): void {
    if (this.questions[this.activeIndex].openaiTrigger) {
      this.suggestQuestionAi();
    } else {
      if (this.activeIndex < this.questions.length - 1) {
        this.activeIndex++;
      }
    }
  }

  prev(): void {
    if (this.activeIndex > 0) {
      this.activeIndex--;
    }
  }

  finish(): void {
    const answerMap: AnswerDTO[] = Object.entries(this.answers).map(([key, value]) => ({
      questionId: Number(key),
      answerText: value,
    }));
    const response: ResponseDTO = {
      form: { id: this.form.formId },
      submittedAt: new Date(),
      answers: answerMap,
    };
    this.responseService.saveResponse(response).subscribe({
      next: () => {
        this.router.navigate(['/form-status'], { queryParams: { status: 'success' } });
      },
      error: () => {
        this.router.navigate(['/form-status'], { queryParams: { status: 'error' } });
      },
    });
  }

  suggestQuestionAi() {
    const keys = Object.keys(this.answers).map((k) => Number(k));
    const lastKey = Math.max(...keys);
    const lastValue = this.answers[lastKey];
    const body = {
      position: this.form.title,
      description: this.form.description,
      previousQuestion: this.questions[this.activeIndex].text,
      answer: lastValue,
    };
    this.apiAiService.suggestQuestion(body).subscribe((response: any) => {
      const type: 'TEXT' | 'NUMBER' | 'SELECT' = 'TEXT';
      const newQuestion = {
        generatedByIa: true,
        openaiTrigger: false,
        text: response.content,
        type: type,
      };
      this.questions.splice(this.activeIndex + 1, 0, newQuestion);
      this.activeIndex++;
    });
  }
}
