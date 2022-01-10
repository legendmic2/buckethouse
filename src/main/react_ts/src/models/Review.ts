import IReview from "../types/Review";
import RootStore from "../stores/RootStore";
import {computed, makeObservable} from "mobx";

export default class Review implements IReview{
    id: string;
    productId: string;
    userId: string;
    title: string;
    content: string;
    grade: number;

    constructor(private store: RootStore, review: IReview) {
        this.id = review.id;
        this.productId = review.productId;
        this.userId = review.userId;
        this.title = review.title;
        this.content = review.content;
        this.grade = review.grade;

        makeObservable(this);
    }

    @computed get storeContent() {
        return this.store.product.all.find((it) => it.id === this.productId);
    }

    @computed get user() {
        return this.store.user.all.find((it) => it.id === this.userId);
    }
}