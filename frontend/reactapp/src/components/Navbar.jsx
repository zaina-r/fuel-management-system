import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import Authentication from "../apiservice/Authentication";

// import { motion } from "motion/react";
// import { useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();
  const isAuthenticated = Authentication.isAuthenticated();
  const isFuelStationOwner = Authentication.isFuelStationOwner();
  const isVehicleOwner = Authentication.isVehicleOwner();

  const handleLogout = () => {
    Authentication.logout();
    navigate("/");
  };
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
              <li className="hover:text-gray-300 cursor-pointer">
                <NavLink to="/">Home</NavLink>
              </li>
              <li className="hover:text-gray-300 cursor-pointer">
                <NavLink to="/about">About</NavLink>
              </li>
              <li className="hover:text-gray-300 cursor-pointer">Service</li>
              <li className="hover:text-gray-300 cursor-pointer">Contact</li>
              {isVehicleOwner && (
                <li className="hover:text-gray-300 cursor-pointer">
                 <NavLink to="/vehicleRegister">VehicleRegister</NavLink> 
                </li>
              )}
              {isFuelStationOwner && (
                <li className="hover:text-gray-300 cursor-pointer">
                  <NavLink to="/stationRegister">FuelStationRegister</NavLink>
                </li>
              )}
            </ul>
          </div>
          {!isAuthenticated && (
            <div className="flex gap-3">
              <div className="hidden md:block">
                <button className="primary-btn ">
                  <NavLink to="/login">Sign in</NavLink>
                </button>
              </div>
              <div className="hidden md:block">
                <button className="primary-btn">
                  <NavLink to="/register">Sign up</NavLink>{" "}
                </button>
              </div>
            </div>
          )}
          {isAuthenticated && (
            <div>
              <button className="primary-btn" onClick={handleLogout}>
                Sign out
              </button>
            </div>
          )}

          <div className="md:hidden">
            <button className="text-white"></button>
          </div>
        </div>
      </div>
    </>
  );
};

export default Navbar;
