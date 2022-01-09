import axios from "axios";
import RootStore from "../stores/RootStore";

export default class RootApi {
    client = axios.create({baseURL: "http://localhost:8080"});

    constructor(store: RootStore) {
    }
}