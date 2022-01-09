import RootApi from "./RootApi";
import RootStore from "../stores/RootStore";

export default class ProductApi {
    constructor(private api: RootApi, private store: RootStore) {
    }

    async getAll() {
        const res = await this.api.client.get(`/store`);
        this.store.user.load(res.data);
    }
}