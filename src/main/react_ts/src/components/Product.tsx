import {observer} from "mobx-react-lite";
import IProduct from "../types/Product";

export interface Props {
    product: IProduct
}

const Product = observer((props: Props) => {
    return (
        <div>
            Hello, Product!
        </div>
    )
});

export default Product;