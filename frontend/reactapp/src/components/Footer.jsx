import React from "react";
import { FaFacebook, FaTwitter, FaInstagram, FaLinkedin } from "react-icons/fa";

const Footer = () => {
  return (
    <footer className="bg-gray-900 text-gray-300 py-8 ">
      <div className="container mx-auto px-6">
      
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
  
          <div>
            <h3 className="text-xl font-semibold text-white mb-4">About Fuel QR</h3>
            <p className="text-sm">
              Fuel QR is a digital platform in Sri Lanka designed to streamline fuel services by integrating QR code technology for efficient distribution and management.
            </p>
          </div>

          <div>
            <h3 className="text-xl font-semibold text-white mb-4">Quick Links</h3>
            <ul className="space-y-2">
              <li>
                <a href="#" className="hover:text-white">
                  Home
                </a>
              </li>
              <li>
                <a href="#" className="hover:text-white">
                  About Us
                </a>
              </li>
              <li>
                <a href="#" className="hover:text-white">
                  Services
                </a>
              </li>
              <li>
                <a href="#" className="hover:text-white">
                  Contact
                </a>
              </li>
            </ul>
          </div>

         
          <div>
            <h3 className="text-xl font-semibold text-white mb-4">Contact Us</h3>
            <p className="text-sm">123, Galle Road, Colombo, Sri Lanka</p>
            <p className="text-sm">Email: support@fuelqr.lk</p>
            <p className="text-sm">Phone: +94 11 2345678</p>
            <div className="flex space-x-4 mt-4">
              <a href="#" className="text-gray-400 hover:text-blue-500">
                <FaFacebook size={24} />
              </a>
              <a href="#" className="text-gray-400 hover:text-blue-400">
                <FaTwitter size={24} />
              </a>
              <a href="#" className="text-gray-400 hover:text-pink-500">
                <FaInstagram size={24} />
              </a>
              <a href="#" className="text-gray-400 hover:text-blue-600">
                <FaLinkedin size={24} />
              </a>
            </div>
          </div>
        </div>


        <div className="mt-8 border-t border-gray-700 pt-4 text-center text-sm">
          <p>&copy; 2025 Fuel QR. All rights reserved.</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;

