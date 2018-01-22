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
        this.messagesService.addMessage({ type: MessageType.info, message: "Layout : info" } as Message)
        this.getMessages();
    }

    getMessages(): void {
        this.messages = this.messagesService.getMessages();
    }
}
