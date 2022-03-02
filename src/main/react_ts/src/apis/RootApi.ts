import RootStore from "../stores/RootStore";
import ProductApi from "./ProductApi";
import UserApi from "./UserApi";
import {axiosUtil} from "../utils/AxiosUtil";

export default class RootApi {
    client = axiosUtil;

    product: ProductApi;
    user: UserApi;

    // todo: user, review 추가

    // todo: AxiosUtil 생성

    constructor(store: RootStore) {
        this.product = new ProductApi(this, store);
        this.user = new UserApi(this, store);
    }
}