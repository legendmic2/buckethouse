import {action, computed, makeAutoObservable, observable} from "mobx";
import RootStore from "./RootStore";
import Product from "../models/Product";
import IProduct from "../types/Product";


export default class ProductStore {

    byId = observable.map<string, Product>();

    constructor(private store: RootStore) {
        makeAutoObservable(this);
    }

    @action load(products: IProduct[]) {
        products.forEach((it) => this.byId.set(it.id, new Product(this.store, it)));
    }

    @computed get all() {
        return Array.from(this.byId.values());
    }

    @action add(product: IProduct) {

    }

}