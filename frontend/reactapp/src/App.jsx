import { useState } from "react";
import "./App.css";
import UserLogin from "./pages/UserLogin";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import UserRegisterationForm from "./pages/UserRegisterationForm";
import VehicleRegistration from "./pages/VehicleRegistration";

import Navbar from "./components/Navbar";

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
              <Route path="/register" element={<VehicleRegistration />} />
              

            </Routes>
          </div>
        </div>
      </BrowserRouter>
    </>
  );
}

export default App;
