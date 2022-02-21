import RootApi from "./RootApi";
import RootStore from "../stores/RootStore";
import IProduct, {IProductList} from "../types/Product";

export default class ProductApi {
    constructor(private api: RootApi, private store: RootStore) {
    }

    async getAll() {
        const res = await this.api.client.get(`/store`); // todo: AxiosResponse 이용하여 AxiosUtil 생성
        this.store.product.load(res.data.data);
    }

    async create(newProduct: IProduct) {
        const res = await this.api.client.put<IProductList>(`/store`);

        const createdProduct = res.data.data.find(product => product.title === newProduct.title);

        if(createdProduct)  {
            this.store.product.add(createdProduct);
        }

        if(res.data) {

        }

    }
}