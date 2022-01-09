import UserStore from "./UserStore";
import ProductStore from "./ProductStore";
import ReviewStore from "./ReviewStore";

export default class RootStore {
    user = new UserStore(this);
    storeContent = new ProductStore(this);
    review = new ReviewStore(this);
}