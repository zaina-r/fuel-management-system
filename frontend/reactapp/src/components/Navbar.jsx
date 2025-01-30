import React, { useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import Authentication from "../apiservice/Authentication";
import { SlideDown } from "../animation/direction";
import { motion } from "framer-motion";
import UserAccountApi from "../apiservice/UserAccountApi";
import Error from "../responseDisplay/Error";
import Success from "../responseDisplay/Success";

// import { motion } from "motion/react";
// import { useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();
  const isAuthenticated = Authentication.isAuthenticated();
  const isFuelStationOwner = Authentication.isFuelStationOwner();
  const isVehicleOwner = Authentication.isVehicleOwner();
  const isAdmin = Authentication.isAdmin();
  const [userData, setUserData] = useState(null);
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  useEffect(() => {
    if (isAuthenticated) {
      getUser();
    }
  }, [isAuthenticated, userData]);

  const getUser = async () => {
    try {
      const response = await UserAccountApi.getUserDetails();

      setUserData(response.userAccountDto);
      console.log(response.userAccountDto);

      console.log(userData);

      setError("");
    } catch (err) {
      setError(err.message || "An unexpected error occurred.");
      setSuccess("");
    }
  };

  const handleLogout = () => {
    Authentication.logout();
    navigate("/");
  };
  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  const formatUsername = () => {
    if (isAdmin) {
      return "Admin";
    } else if (isVehicleOwner && userData) {
      return `${userData.firstname.charAt(0)}${userData.lastname}`;
    } else if (isFuelStationOwner && userData) {
      return userData.licenseNumber;
    }
    return "";
  };

  return (
    <>
      <motion.div
        variants={SlideDown(0.2)}
        initial="hidden"
        whileInView={"visible"}
        className=" absolute top-0 z-50 w-full"
      >
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
              {isVehicleOwner && (
                <li className="hover:text-gray-300 cursor-pointer">
                  <NavLink to="/vehicleHistory">VehicleHistory</NavLink>
                </li>
              )}
              {isFuelStationOwner && (
                <li className="hover:text-gray-300 cursor-pointer">
                  <NavLink to="/stationRegister">FuelStationRegister</NavLink>
                </li>
              )}
              {isFuelStationOwner && (
                <li className="hover:text-gray-300 cursor-pointer">
                  <NavLink to="/StationHistory">StationHistory</NavLink>
                </li>
              )}
              {isAdmin && (
                <li className="hover:text-gray-300 cursor-pointer">
                  <NavLink to="/admin">AdminPanel</NavLink>
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
          {isAuthenticated && userData && (
            <div className="relative">
              <div
                className="flex items-center cursor-pointer"
                onClick={toggleDropdown}
              >
                <img
                  src="https://via.placeholder.com/150"
                  alt="User Avatar"
                  className="w-8 h-8 rounded-full mr-2"
                />
                <span>{formatUsername()}</span>
              </div>
              {isDropdownOpen && (
                <div className="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg py-2">
                  <NavLink
                    to="/profile"
                    className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  >
                    Profile
                  </NavLink>
                  <button
                    onClick={handleLogout}
                    className="block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  >
                    Sign out
                  </button>
                </div>
              )}
            </div>
          )}

          <div className="md:hidden">
            <button className="text-white"></button>
          </div>
        </div>
      </motion.div>
    </>
  );
};

export default Navbar;
