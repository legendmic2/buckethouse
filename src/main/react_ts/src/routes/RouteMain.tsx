import React from 'react';
import {Navigate, Route, Routes} from 'react-router-dom'
import AppContext from "../app-context";
import HomePage from "../pages/HomePage";
import RootStore from "../stores/RootStore";
import RootApi from "../apis/RootApi";
import Blog from "../pages/blogTest/Blog";
import ProductMain from "./ProductMain";
import ProductCreate from "../pages/ProductCreate";
import LoginPage from "../pages/LoginPage";
import SignUpPage from "../pages/SignUpPage";

const store = new RootStore();
const api = new RootApi(store);

const RouteMain = () => {
    // if(cookie is empty) redirect to loginpage;

    return (
        <AppContext.Provider value={{store, api}}>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/blog" element={<Blog/>}/>
                <Route path="/product" element={<ProductMain/>}/>
                <Route path="/product/create" element={<ProductCreate/>}/>
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/signup" element={<SignUpPage/>}/>
                <Route path="*" element={<Navigate replace to="/"/>}/>
            </Routes>
        </AppContext.Provider>
    );
}

export default RouteMain;
