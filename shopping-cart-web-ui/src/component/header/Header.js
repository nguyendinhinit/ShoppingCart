import React from 'react';
import {Link} from "react-router-dom";

const Header = () => {
    return (
        <div>
            {/* Pre Header */}
            <div id="pre-header">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <span>Đây Sản phẩm được copy từ Internet để hoàn thiện trong quá trình rảnh rỗi và không biết làm gì khi bị a LM dí</span>
                        </div>
                    </div>
                </div>
            </div>
            {/* Navigation */}
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark static-top">
                <div className="container">
                    <a className="navbar-brand" href="#"><img src="assets/images/header-logo.png" alt=""/></a>
                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"/>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarResponsive">
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <Link className="nav-link" to={'/index'}>Home</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to={'/products'}>Products</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to={'/about'}>About Us</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/contact">Contact Us</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    );
};

export default Header;