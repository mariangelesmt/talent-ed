import { FormQuestion } from './formQuestion.model';

export class Form {
  id?: number | null = null;
  title: string | null = null;
  description: string | null = null;
  questions: FormQuestion[] | null = null;
  offerId: number | null = null;
}
