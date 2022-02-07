import React, {useEffect, useState} from 'react';
import {observer} from "mobx-react-lite";
import {useAppContext} from "../app-context";
import Product from "../components/Product";

const HomePage = observer(() => {
    const {api, store} = useAppContext();
    const [loading, setLoading] = useState(false);

    const load = async () => {
        try {
            setLoading(true);
            await api.product.getAll();
            // User, Review
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        load();
    }, []);

    if (loading) {
        return <div>loading...</div>;
    }

    return (
        <div>
            <h1>Products</h1>
            {store.product.all.map((product) => (
                <Product product={product} key={product.id}/>
            ))}
        </div>
    );
});

export default HomePage;
