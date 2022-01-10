import RootApi from "./RootApi";
import RootStore from "../stores/RootStore";

export default class ProductApi {
    constructor(private api: RootApi, private store: RootStore) {
    }

    async getAll() {
        const res = await this.api.client.get(`/store`); // todo: AxiosResponse 이용하여 AxiosUtil 생성
        this.store.product.load(res.data.data);
    }
}