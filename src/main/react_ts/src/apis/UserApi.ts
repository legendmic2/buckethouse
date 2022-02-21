import RootApi from "./RootApi";
import RootStore from "../stores/RootStore";
import IUser from "../types/User";
import {AxiosError} from "axios";

export default class UserApi {
    constructor(private api: RootApi, private store: RootStore) {
    }

    async login(loginUser: IUser) {
        const res = await this.api.client.post<IUser | AxiosError>(`/auth/signin`, loginUser);

        console.log(res);
    }
}