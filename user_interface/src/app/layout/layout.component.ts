import { Component, OnInit } from '@angular/core';
import { Message, MessageType, MessagesService } from '../shared/';

@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
    messages: Array<Message> = [];

    constructor(private messagesService: MessagesService) { }

    ngOnInit() {
        this.getMessages();
    }

    getMessages(): void {
        this.messages = this.messagesService.getMessages();
    }

    closeMessage(id: number): void {
        this.messagesService.deleteMessage(id);
        this.getMessages();
    }
}
