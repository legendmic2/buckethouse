import {observer} from "mobx-react-lite";
import IProduct from "../types/Product";

export interface Props {
    product: IProduct
}

const Product = observer((props: Props) => {
    return (
        <div>
            <div>
                <strong>
                    {props.product.title}
                </strong>
                <p>{props.product.description}</p>
            </div>
            <div>
                {props.product.price}
                {props.product.deliveryFee}
                {props.product.vendorId}
                {props.product.stock}
            </div>
        </div>
    )
});

export default Product;