import React from 'react';
import {observer} from "mobx-react-lite";

export interface IStoreContentProps {
}

const Product = observer((props: IStoreContentProps) => {
        return (
            <>
                HELLO, React with Mobx!
            </>
        );
    })


;

export default Product;
