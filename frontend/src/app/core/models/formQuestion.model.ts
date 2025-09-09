export interface FormQuestion {
  id?: number;
  text: string;
  type: 'TEXT' | 'NUMBER' | 'SELECT';
  openaiTrigger?: boolean;
  originIaQuestionId?: number;
  generatedByIa: boolean;
  formId?: number;
}
