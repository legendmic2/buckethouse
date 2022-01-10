import React from 'react';
import {Route, Routes} from 'react-router-dom'
import AppContext from "../app-context";
import HomePage from "../pages/Home";
import RootStore from "../stores/RootStore";
import RootApi from "../apis/RootApi";

const store = new RootStore();
const api = new RootApi(store);

const RouteMain = () => {
    return (
        <AppContext.Provider value={{store, api}}>
            <Routes>
                <Route path="/*" element={<HomePage/>}/>
            </Routes>
        </AppContext.Provider>
    );
}

export default RouteMain;
