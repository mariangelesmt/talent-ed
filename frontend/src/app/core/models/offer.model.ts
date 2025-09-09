import { Center } from './center.model';

export class Offer {
  public id: number | null = null;
  title: string | null = null;
  center: Center | null = null;
  description: string | null = null;
  requirements: string[] | null = null;
  schedule: string | null = null;
  publishedAt: string | null = null;
}
