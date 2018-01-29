import { Injectable } from '@angular/core';
import { Message } from '../../';

@Injectable()
export class MessagesService {
  private currentId = 0;
  private messages: Message[] = [];

  constructor() { }

  addMessage(message: Message): void {
    message.id = this.currentId++;
    this.messages.push(message);
  }

  deleteMessage(id: number): void {
    this.messages = this.messages.filter(message => message.id != id);
  }

  getMessages(): Message[] {
    return this.messages;
  }
}
