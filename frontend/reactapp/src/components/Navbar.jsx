import React from "react";
import { NavLink, useNavigate } from 'react-router-dom';

// import { motion } from "motion/react";
// import { useNavigate } from "react-router-dom";

const Navbar = () => {
    const navigate = useNavigate();
  return (
    <>
      <div className=" absolute top-0 z-50 w-full">
        <div className="flex flex-wrap justify-between items-center text-white font-bold px-4 py-6 md:px-10 lg:px-36">
          <div>
            <h1 className="text-xl sm:text-2xl">
              <span className="font-play">FuelPass</span>
            </h1>
          </div>

          <div className="hidden md:flex">
            <ul className="flex justify-between items-center space-x-4 sm:space-x-6 lg:space-x-10 text-sm">
              <li className="hover:text-gray-300 cursor-pointer">Home</li>
              <li className="hover:text-gray-300 cursor-pointer">About</li>
              <li className="hover:text-gray-300 cursor-pointer">Service</li>
              <li className="hover:text-gray-300 cursor-pointer">Contact</li>
            </ul>
          </div>

          <div className="hidden md:block">
            <button className="primary-btn">Log in</button>
          </div>

          <div className="md:hidden">
            <button className="text-white"></button>
          </div>
        </div>
      </div>
    </>
  );
};

export default Navbar;
