import React from 'react';
import ReactDOM from 'react-dom';
import RouteMain from './routes/RouteMain';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter as Router} from "react-router-dom";

ReactDOM.render(
    <Router>
        <React.StrictMode>
            <RouteMain/>
        </React.StrictMode>
    </Router>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
