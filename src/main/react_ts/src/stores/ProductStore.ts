import {action, computed, makeAutoObservable, observable} from "mobx";
import AppStore from "./AppStore";
import Product from "../models/Product";
import IProduct from "../types/Product";


export default class ProductStore {

    byId = observable.map<string, Product>();

    constructor(private store: AppStore) {
        makeAutoObservable(this);
    }

    @action load(storeContents: IProduct[]) {
        storeContents.forEach((it) => this.byId.set(it.id, new Product(this.store, it)));
    }

    @computed get all() {
        return Array.from(this.byId.values());
    }

}