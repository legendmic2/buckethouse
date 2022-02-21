import RootStore from "../stores/RootStore";
import IUser from "../types/User";
import {computed, makeObservable} from "mobx";

export default class User implements IUser {
    token: string;
    id: string;
    username: string;
    email: string;
    password: string;

    constructor(private store: RootStore, user: IUser) {
        this.token = user.token;
        this.id = user.id;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;

        makeObservable(this);
    }

    @computed get storeContents() {
        return this.store.product.all.filter((it) => it.vendorId === this.id);
    }

    @computed get reviews() {
        return this.store.review.all.filter((it) => it.userId ===this.id);
    }
}
