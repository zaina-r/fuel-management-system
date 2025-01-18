import { useState } from "react";
import "./App.css";
import UserLogin from "./pages/UserLogin";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import UserRegisterationForm from "./pages/UserRegisterationForm";

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
         
              

            </Routes>
          </div>
        </div>
      </BrowserRouter>
    </>
  );
}

export default App;
