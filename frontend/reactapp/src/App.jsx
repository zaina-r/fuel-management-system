import React from "react";
import { useState } from "react";
import "./App.css";
import UserLogin from "./pages/UserLogin";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import UserRegisterationForm from "./pages/UserRegisterationForm";
import ForgotPassword from "./pages/Forgotpassword";
import Navbar from "./components/Navbar";
import VehicleRegistration from "./pages/VehicleRegistration";
import StationRegistration from "./pages/StationRegistration";
import Contact from "./pages/Contact";

import AdminNavbar from "./components/AdminNavbar";
import AdminHome from "./AdminPages/AdminHome";
import AdminStationRegistration from "./AdminPages/AdminStationRegistration";

import About from "./pages/About";
import DisplayVehicleDetails from "./pages/DisplayVehicleDetails";
import DisplayStationDetails from "./pages/DisplayStationDetails";

function App() {
  return (
    <>
      <BrowserRouter>
        <div className="fuelPass">
          <Navbar />
          <div className="content">
            <Routes>
              <Route exact path="/" element={<Home />} />
              <Route exact path="/about" element={<About />} />
              <Route exact path="/login" element={<UserLogin />} />
              <Route path="/register" element={<UserRegisterationForm />} />
              <Route path="/vehicleRegister" element={<VehicleRegistration />}/>
              <Route path="/vehicleHistory" element={<DisplayVehicleDetails />}/>
              <Route path="/forgotpassword" element={<ForgotPassword />} />
              <Route path="/stationRegister" element={<StationRegistration />}/>
              <Route path="/admin" element={<AdminHome />} />
              <Route path="/StationHistory" element={<DisplayStationDetails />} />
              <Route path="/contact" element={<Contact />} />
            </Routes>
          </div>
        </div>
        <AdminNavbar />
      </BrowserRouter>
    </>
  );
}

export default App;
