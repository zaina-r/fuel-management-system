import { useState } from "react";
import "./App.css";
import UserLogin from "./pages/UserLogin";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from "./pages/Home";

import Navbar from './components/Navbar';


function App() {
  return (
    <>
    // <div>
    //   <UserLogin />
    // </div>
    <BrowserRouter>
         <div className="fuelPass">
               <Navbar/>
               <div className="content">
                  <Routes>
                     <Route exact path="/" element={<Home />} />'
                     {/* <Route exact path="/login" element={<UserLogin />} /> */}
                     
                  </Routes>
 
               </div>
         </div>
    
    </BrowserRouter>
    <div className="VehicleRegistration">
      <Header/>
      <VehicleRegistration />
    </div>
    </>
  );
}

export default App;
