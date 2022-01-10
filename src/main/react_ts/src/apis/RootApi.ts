import axios from "axios";
import RootStore from "../stores/RootStore";
import ProductApi from "./ProductApi";

export default class RootApi {
    client = axios.create({baseURL: "http://localhost:8080"});

    product: ProductApi;
    // todo: user, review 추가

    constructor(store: RootStore) {
        this.product = new ProductApi(this, store);
    }
}