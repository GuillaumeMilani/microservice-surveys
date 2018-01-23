import { Injectable } from '@angular/core';

@Injectable()
export class SessionService {
  detailUrl: string;

  constructor() { }

  setDetailUrl(detailUrl: string): void {
    this.detailUrl = detailUrl;
  }

  getDetailUrl(): string {
    return this.detailUrl;
  }
}
