import React from 'react';
const About = () => {
    return (
        <div>
            {/* Page Content */}
            {/* About Page Starts Here */}
            <div className="about-page">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <div className="section-heading">
                                <div className="line-dec"/>
                                <h1>About Us</h1>
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="left-image">
                                <img src="assets/images/about-us.jpg" alt=""/>
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="right-content">
                                <h4>Phasellus vel interdum elit</h4>
                                <p><a href="https://www.pexels.com/photo/group-of-people-raising-right-hand-1059120/">Photo
                                    Credit</a> goes to Pexels website. Aliquam erat volutpat. Pellentesque fringilla,
                                    ligula consectetur cursus scelerisque, leo elit pellentesque sapien, et pharetra
                                    arcu elit vitae sem. Suspendisse erat dui, condimentum in mi ac, varius tempor
                                    sapien. Donec in justo sit amet ex aliquet maximus ac quis erat.</p>
                                <br/>
                                <p>Donec fermentum tincidunt tellus quis fermentum. Proin eget imperdiet purus. Nulla
                                    facilisi. Aliquam tincidunt porttitor dui sed euismod. Duis est libero, rhoncus
                                    fermentum turpis id, tempus fringilla ipsum. Nullam venenatis tincidunt neque vel
                                    hendrerit. Suspendisse porta pretium porttitor.</p>
                                <br/>
                                <p>Sed purus quam, ultricies eu leo in, sollicitudin varius quam. Proin dictum urna ac
                                    diam fermentum tempus. Pellentesque urna urna, ullamcorper a tincidunt dignissim,
                                    venenatis in nisi. Vivamus in volutpat tellus. Etiam fermentum luctus posuere.</p>
                                <br/>
                                <p><a rel="nofollow" href="https://www.tooplate.com/view/2114-pixie">Pixie HTML
                                    Template</a> can be converted into your desired CMS theme. You can use this
                                    Bootstrap v4.1.3 layout for any online shop. Please tell your friends about <a
                                        rel="nofollow" href="https://www.facebook.com/tooplate/">Tooplate</a>. Thank
                                    you.</p>
                                <div className="share">
                                    <h6>Find us on: <span><a href="#"><i className="fa fa-facebook"/></a><a href="#"><i
                                        className="fa fa-linkedin"/></a><a href="#"><i
                                        className="fa fa-twitter"/></a></span></h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/* About Page Ends Here */}
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

export default About;