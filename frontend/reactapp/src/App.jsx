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


function App() {
  return (
    <>
      <BrowserRouter>
        <div className="fuelPass">
          <Navbar />
          <div className="content">
            <Routes>
              <Route exact path="/" element={<Home />} />
              <Route exact path="/login" element={<UserLogin />} />
              <Route path="/register" element={<UserRegisterationForm />} />
              <Route
                path="/vehicleRegister"
                element={<VehicleRegistration />}
              />
              <Route path="/forgotpassword" element={<ForgotPassword />} />
              <Route
                path="/stationRegister"
                element={<StationRegistration />}
              />
              <Route path="/contact" element={<Contact />} />
            </Routes>
          </div>
        </div>
      </BrowserRouter>
    </>
  );
}

export default App;
