export default interface IReview {
    id: string;
    productId: string;
    userId: string;
    title: string;
    content: string;
    grade: number;
}