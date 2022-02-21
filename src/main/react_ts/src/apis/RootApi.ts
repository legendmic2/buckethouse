import axios, {AxiosInstance} from "axios";
import RootStore from "../stores/RootStore";
import ProductApi from "./ProductApi";
import UserApi from "./UserApi";

export default class RootApi {
    client = axios.create({baseURL: "http://localhost:8080"});

    product: ProductApi;
    user: UserApi;

    // todo: user, review 추가

    // todo: AxiosUtil 생성

    constructor(store: RootStore) {
        this.product = new ProductApi(this, store);
        this.user = new UserApi(this, store);
    }
}