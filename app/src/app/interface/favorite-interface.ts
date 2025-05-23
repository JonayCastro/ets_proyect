import SneakerInterface from "./sneaker-interface";

export default interface FavoriteInterface{

    idFavorite: number;
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
}