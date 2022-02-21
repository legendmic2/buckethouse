import React from 'react';
import {Route, Routes} from 'react-router-dom'
import Blog from "../pages/blogTest/Blog";
import CreateProductComponent from "../pages/blogTest/CreateProductComponent";

const ProductMain = () => {
    return (
        <Routes>
            <Route path="/" element={<Blog/>}>
                {/*<Route index={true} element={<Blog/>}/>*/}
                <Route path="create" element={<CreateProductComponent/>}/>
            </Route>
        </Routes>
    )
}

export default ProductMain;