import React from "react";
import {Link} from 'react-router-dom'


function Footer() {
  return (
    <footer>
    <div className="footer-top-area footer-bg">
      <div className="container">
        <div className="row justify-content-between">
          <div className="col-3 mx-0 px-0">
            <div className="footer-widget">
              <div className="f-logo">
                <Link to="/"><img src="/img/logo/UNIFAS-200px.png" alt="" style={{maxWidth : "20%"}} /></Link>
              </div>
              <div className="footer-text">
                <p>Wear healthy, feel healthy. Our clothes are made from natural and organic materials</p>
              </div>
              <div className="footer-contact">
                <div className="icon"><i className="fas fa-headphones" /></div>
                <div className="content">
                  <h4 className="title"><a href="tel:0987654321"> 0989-534-458</a></h4>
                  <span>Call Now</span>
                </div>
              </div>
              <div className="footer-social">
                <ul>
                  <li><a href="/#"><i className="fab fa-facebook-f" /></a></li>
                  <li><a href="/#"><i className="fab fa-twitter" /></a></li>
                  <li><a href="/#"><i className="fab fa-youtube" /></a></li>
                  <li><a href="/#"><i className="fab fa-linkedin-in" /></a></li>
                </ul>
              </div>
            </div>
          </div>
          <div className="col-2 mx-0 px-0">
            <div className="footer-widget">
              <h4 className="fw-title">About Unifas</h4>
              <div className="fw-link">
                <ul>
                  <li><Link to="/contacts">Information</Link></li>
                  <li><Link to="/contacts">Store Locator</Link></li>
                  <li><Link to="/contacts">Career</Link></li>
                </ul>
              </div>
            </div>
          </div>
          <div className="col-2 mx-0 px-0">
            <div className="footer-widget">
              <h4 className="fw-title">Help</h4>
              <div className="fw-link">
                <ul>
                  <li><Link to="/breeder">FAQ</Link></li>
                  <li><Link to="/adoption">Return Policy</Link></li>
                  <li><Link to="/contacts">Privacy Policy</Link></li>
                  <li><Link to="/contacts">Return Policy</Link></li>
                  <li><Link to="/contacts">Accessibility</Link></li>
                </ul>
              </div>
            </div>
          </div>
          <div className="col-2 mx-0 px-0">
            <div className="footer-widget">
              <h4 className="fw-title">Account</h4>
              <div className="fw-link">
                <ul>
                  <li><Link to="/breeder">Membership</Link></li>
                  <li><Link to="/adoption">Profile</Link></li>
                  <li><Link to="/contacts">Coupons</Link></li>
                </ul>
              </div>
            </div>
          </div>
          <div className="col-3 mx-0 px-0">
            <div className="footer-widget">
              <h4 className="fw-title">E-Newsletter</h4>
              <div className="fw-link">
                <ul>
                  <li><span style={{fontSize : "14px", color : "#676666"}}>Sign up and be the first-in-the know about new arrivals, promotions, in-store events and more.</span></li>
                  <li><h1><Link to="/adoption" style={{fontSize : "15px", fontWeight : "900", backgroundColor : "red", padding : "9px", borderRadius : "15px", color : "white"}}>SUBSCRIBE NOW</Link></h1></li>
                </ul>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
    <div className="copyright-area">
      <div className="container">
        <div className="row">
          <div className="col-md-8">
            <div className="copyright-text">
              <p>Copyright © 2023 UNIFAS. All Rights Reserved.</p>
            </div>
          </div>
          <div className="col-md-4 d-none d-md-block">
            {/*<div className="footer-lang">*/}
            {/*  <div className="dropdown">*/}
            {/*    <button className="dropdown-toggle" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">*/}
            {/*      <img src="img/icon/united-states.png" alt="" /> English*/}
            {/*    </button>*/}
                {/*<div className="dropdown-menu" aria-labelledby="dropdownMenuButton2">*/}
                {/*  <Link className="dropdown-item" to="/"><img src="img/icon/russia.png" alt="" />Russia</Link>*/}
                {/*  <Link className="dropdown-item" to="/"><img src="img/icon//thailand.png" alt="" />Thailand</Link>*/}
                {/*  <Link className="dropdown-item" to="/"><img src="img/icon/india.png" alt="" />India</Link>*/}
                {/*</div>*/}
            {/*  </div>*/}
              {/*</div>*/}
            {/*</div>*/}
          </div>
        </div>
      </div>
    </div>
  </footer>

  )
}

export default Footer;
