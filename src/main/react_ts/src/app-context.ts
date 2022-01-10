import React, { useContext } from "react";
import RootStore from "./stores/RootStore";
import RootApi from "./apis/RootApi";

interface AppContextType {
    store: RootStore;
    api: RootApi;
}

const AppContext = React.createContext<null | AppContextType>(null);

export const useAppContext = () => {
    const context = useContext(AppContext);
    return context as AppContextType;
};

export default AppContext;