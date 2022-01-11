import React from 'react';
import {Navigate, Route, Routes} from 'react-router-dom'
import AppContext from "../app-context";
import HomePage from "../pages/Home";
import RootStore from "../stores/RootStore";
import RootApi from "../apis/RootApi";
import Blog from "../pages/blogTest/Blog";

const store = new RootStore();
const api = new RootApi(store);

const RouteMain = () => {
    return (
        <AppContext.Provider value={{store, api}}>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/blog" element={<Blog/>}/>
                <Route path="*" element={<Navigate replace to="/"/>}/>
            </Routes>
        </AppContext.Provider>
    );
}

export default RouteMain;
