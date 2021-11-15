import React from 'react';
import {Link} from "react-router-dom";

const Product = () => {
    return (
        <div>
            {/* Page Content */}
            {/* Items Starts Here */}
            <div className="featured-page">
                <div className="container">
                    <div className="row">
                        <div className="col-md-4 col-sm-12">
                            <div className="section-heading">
                                <div className="line-dec"/>
                                <h1>Featured Items</h1>
                            </div>
                        </div>
                        <div className="col-md-8 col-sm-12">
                            <div id="filters" className="button-group">
                                <button className="btn btn-primary" data-filter="*">All Products</button>
                                <button className="btn btn-primary" data-filter=".new">Newest</button>
                                <button className="btn btn-primary" data-filter=".low">Low Price</button>
                                <button className="btn btn-primary" data-filter=".high">Hight Price</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="featured container no-gutter">
                <div className="row posts">
                    <div id={1} className="item new col-md-4">
                        <Link to="single-product">
                            <div className="featured-item">
                                <img src="assets/images/product-01.jpg" alt=""/>
                                <h4>Proin vel ligula</h4>
                                <h6>$15.00</h6>
                            </div>
                        </Link>
                    </div>
                    <div id={2} className="item high col-md-4">
                        <a href="single-product.html">
                            <div className="featured-item">
                                <img src="assets/images/product-02.jpg" alt=""/>
                                <h4>Erat odio rhoncus</h4>
                                <h6>$25.00</h6>
                            </div>
                        </a>
                    </div>
                    <div id={3} className="item low col-md-4">
                        <a href="single-product.html">
                            <div className="featured-item">
                                <img src="assets/images/product-03.jpg" alt=""/>
                                <h4>Integer vel turpis</h4>
                                <h6>$35.00</h6>
                            </div>
                        </a>
                    </div>
                    <div id={4} className="item low col-md-4">
                        <a href="single-product.html">
                            <div className="featured-item">
                                <img src="assets/images/product-04.jpg" alt=""/>
                                <h4>Sed purus quam</h4>
                                <h6>$45.00</h6>
                            </div>
                        </a>
                    </div>
                    <div id={5} className="item new high col-md-4">
                        <a href="single-product.html">
                            <div className="featured-item">
                                <img src="assets/images/product-05.jpg" alt=""/>
                                <h4>Morbi aliquet</h4>
                                <h6>$55.00</h6>
                            </div>
                        </a>
                    </div>
                    <div id={6} className="item new col-md-4">
                        <a href="single-product.html">
                            <div className="featured-item">
                                <img src="assets/images/product-06.jpg" alt=""/>
                                <h4>Urna ac diam</h4>
                                <h6>$65.00</h6>
                            </div>
                        </a>
                    </div>
                    <div id={7} className="item new high col-md-4">
                        <a href="single-product.html">
                            <div className="featured-item">
                                <img src="assets/images/product-03.jpg" alt=""/>
                                <h4>Proin eget imperdiet</h4>
                                <h6>$75.00</h6>
                            </div>
                        </a>
                    </div>
                    <div id={8} className="item low new col-md-4">
                        <a href="single-product.html">
                            <div className="featured-item">
                                <img src="assets/images/product-02.jpg" alt=""/>
                                <h4>Nullam risus nisl</h4>
                                <h6>$85.00</h6>
                            </div>
                        </a>
                    </div>
                    <div id={9} className="item new col-md-4">
                        <a href="single-product.html">
                            <div className="featured-item">
                                <img src="assets/images/product-01.jpg" alt=""/>
                                <h4>Cras tempus</h4>
                                <h6>$95.00</h6>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <div className="page-navigation">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <ul>
                                <li className="current-page"><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#"><i className="fa fa-angle-right"/></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            {/* Featred Page Ends Here */}
            {/* Subscribe Form Starts Here */}
            <div className="subscribe-form">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <div className="section-heading">
                                <div className="line-dec"/>
                                <h1>Subscribe on PIXIE now!</h1>
                            </div>
                        </div>
                        <div className="col-md-8 offset-md-2">
                            <div className="main-content">
                                <p>Godard four dollar toast prism, authentic heirloom raw denim messenger bag gochujang
                                    put a bird on it celiac readymade vice.</p>
                                <div className="container">
                                    <form id="subscribe" action method="get">
                                        <div className="row">
                                            <div className="col-md-7">
                                                <fieldset>
                                                    <input name="email" type="text" className="form-control" id="email"
                                                           onFocus="if(this.value == 'Your Email...') { this.value = ''; }"
                                                           onBlur="if(this.value == '') { this.value = 'Your Email...';}"
                                                           defaultValue="Your Email..." required/>
                                                </fieldset>
                                            </div>
                                            <div className="col-md-5">
                                                <fieldset>
                                                    <button type="submit" id="form-submit" className="button">Subscribe
                                                        Now!
                                                    </button>
                                                </fieldset>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/* Subscribe Form Ends Here */}
            {/* Footer Starts Here */}
            <div className="footer">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <div className="logo">
                                <img src="assets/images/header-logo.png" alt=""/>
                            </div>
                        </div>
                        <div className="col-md-12">
                            <div className="footer-menu">
                                <ul>
                                    <li><a href="#">Home</a></li>
                                    <li><a href="#">Help</a></li>
                                    <li><a href="#">Privacy Policy</a></li>
                                    <li><a href="#">How It Works ?</a></li>
                                    <li><a href="#">Contact Us</a></li>
                                </ul>
                            </div>
                        </div>
                        <div className="col-md-12">
                            <div className="social-icons">
                                <ul>
                                    <li><a href="#"><i className="fa fa-facebook"/></a></li>
                                    <li><a href="#"><i className="fa fa-twitter"/></a></li>
                                    <li><a href="#"><i className="fa fa-linkedin"/></a></li>
                                    <li><a href="#"><i className="fa fa-rss"/></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/* Footer Ends Here */}
            {/* Sub Footer Starts Here */}
            <div className="sub-footer">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <div className="copyright-text">
                                <p>Copyright © 2019 Company Name
                                    - Design: <a rel="nofollow" href="https://www.facebook.com/tooplate">Tooplate</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/* Sub Footer Ends Here */}
            {/* Bootstrap core JavaScript */}
            {/* Additional Scripts */}
        </div>
    );
};

export default Product;