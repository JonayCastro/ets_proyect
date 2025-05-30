import SneakerInterface from "../interface/sneaker-interface";

export default class SneakerDTO implements SneakerInterface{
    sneakerId: number;
    originId: number;
    originCollectionId: number;
    imageAlt: string;
    taxRate: string;
    originalPrice: number;
    priceBase: number;
    taxAmoun: number;
    price: number;
    brand: string;
    image: string;
    link: string;
    name: string;
    reference: string;

    constructor (data: Partial<SneakerInterface>) {
        this.sneakerId = data.sneakerId ?? 0;
        this.originId = data.originId ?? 0;
        this.originCollectionId = data.originCollectionId ?? 0;
        this.imageAlt = data.imageAlt ?? '';
        this.taxRate = data.taxRate ?? '';
        this.originalPrice = data.originalPrice ?? 0;
        this.priceBase = data.priceBase ?? 0;
        this.taxAmoun = data.taxAmoun ?? 0;
        this.price = data.price ?? 0;
        this.brand = data.brand ?? '';
        this.image = data.image ?? '';
        this.link = data.link ?? '';
        this.name = data.name ?? '';
        this.reference = data.reference ?? '';
    }
}