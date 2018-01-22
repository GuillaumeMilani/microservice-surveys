export class Message {
    id:number;
    type: MessageType;
    message: string;
}

export enum MessageType {
    success = "success",
    info = "info",
    warning = "warning",
    error = "error"
}