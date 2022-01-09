import RootApi from "./RootApi";
import RootStore from "../stores/RootStore";

export default class ProductApi {
    constructor(private api: RootApi, private store: RootStore) {
    }
}