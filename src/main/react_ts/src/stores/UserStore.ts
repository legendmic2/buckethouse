import {action, computed, makeObservable, observable} from "mobx";
import User from "../models/User";
import RootStore from "./RootStore";
import IUser from "../types/User";

export default class UserStore {
    byId = observable.map<string, User>();

    constructor(private store: RootStore) {
        makeObservable(this);
    }

    @action load(users: IUser[]) {
        users.forEach((it) => this.byId.set(it.id, new User(this.store, it)));
    }

    @computed get all() {
        return Array.from(this.byId.values());
    }
}