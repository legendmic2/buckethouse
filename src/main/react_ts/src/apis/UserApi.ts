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

        // login 성공시
        if("id" in res.data) {
            window.localStorage.setItem("accessToken", res.data.token);
        }
        // todo: AxiosResponse, AxiosError로 구분할 수 있도록 AxiosUtil 생성
    }
}