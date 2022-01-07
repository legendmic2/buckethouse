import AppStore from "../stores/AppStore";
import IProduct from "../types/Product";
import {computed, makeObservable} from "mobx";

export default class Product implements IProduct{
    id: string;
    vendorId: string;
    title: string;
    price: number;
    deliveryFee: number;
    description: string;
    stock: number;

    constructor(private store: AppStore, product: IProduct) {
        this.id = product.id;
        this.vendorId = product.vendorId;
        this.title = product.title;
        this.price = product.price;
        this.deliveryFee = product.deliveryFee;
        this.description = product.description;
        this.stock = product.stock;

        makeObservable(this);
    }

    @computed get user() {
        return this.store.user.all.find((it) => it.id === this.vendorId);
    }

    @computed get reviews() {
        return this.store.review.all.filter((it) => it.productId === this.id)
    }
}