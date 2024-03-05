import React from "react";
import "../styles/Footbar.css";

const Footbar = () => {
  return (
    <footer class="footer-distributed">
      <div class="footer-right">
        {/* <a href="#">
          <i class="fa fa-facebook"></i>
        </a>
        <a href="#">
          <i class="fa fa-twitter"></i>
        </a>
        <a href="#">
          <i class="fa fa-linkedin"></i>
        </a>
        <a href="#">
          <i class="fa fa-github"></i>
        </a> */}
      </div>

      <div class="footer-left">
        <p class="footer-links">
          <a class="link-1" href="/">
            Home
          </a>

          <a href="/about">About</a>

          <a href="/contact">Contact</a>
        </p>

        <p>Sapariya & Sons &copy; 2024</p>
      </div>
    </footer>
  );
};

export default Footbar;
