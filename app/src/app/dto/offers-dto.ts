import OffersInterface from "../interface/offers-interface";

export default class OffersDTO implements OffersInterface {
    userId: number;
    oldPrice: number;
    sneakerName: string;
    brand: string;
    link: string;
    newPrice: number;
    userName: string;
    chatId: number;

    constructor(data: Partial<OffersInterface>) {
        this.userId = data.userId || 0;
        this.oldPrice = data.oldPrice || 0;
        this.sneakerName = data.sneakerName || '';
        this.brand = data.brand || '';
        this.link = data.link || '';
        this.newPrice = data.newPrice || 0;
        this.userName = data.userName || '';
        this.chatId = data.chatId || 0;
    }
    
}