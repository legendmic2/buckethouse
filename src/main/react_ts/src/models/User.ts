import AppStore from "../stores/AppStore";
import IUser from "../types/User";
import {computed, makeObservable} from "mobx";

export default class User implements IUser {
    id: string;
    username: string;
    email: string;
    password: string;

    constructor(private store: AppStore, user: IUser) {
        this.id = user.id;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;

        makeObservable(this);
    }

    @computed get storeContents() {
        return this.store.storeContent.all.filter((it) => it.vendorId === this.id);
    }

    @computed get reviews() {
        return this.store.review.all.filter((it) => it.userId ===this.id);
    }
}
