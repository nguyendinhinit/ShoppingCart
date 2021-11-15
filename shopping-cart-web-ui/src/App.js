import React, {Fragment} from 'react';

import About from "./component/page/about/About";
import Header from "./component/header/Header";
import {
    BrowserRouter as Router,
    Route,
    Routes
} from "react-router-dom";
import Contact from "./component/page/contact/Contact";
import Product from "./component/page/products/Product";
import Home from "./component/page/index/Index";
import SingleProduct from "./component/page/singleproduct/SingleProduct";

const App = () => {
    return (
        <div>
            <Router>
                <Fragment>
                    <Header/>
                    <Routes>
                        <Route path={"/about"} element={<About/>}/>
                        <Route path={"/index"} element={<Home/>}/>
                        <Route path={"/products"} element={<Product/>}/>
                        <Route path={"/contact"} element={<Contact/>}/>
                        <Route path={"/single-product"} element={<SingleProduct/>}/>
                    </Routes>
                </Fragment>
            </Router>
        </div>
    );
};

export default App;