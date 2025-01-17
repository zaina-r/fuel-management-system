import { useState } from "react";
import "./App.css";
import UserLogin from "./pages/UserLogin";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from "./pages/Home";
import VehicleRegistration from "./pages/VehicleRegistration";
import Navbar from './components/Navbar';
import Navbar2 from './components/Navbar2';


function App() {
  return (
    <>
     <div>
      rfrfcrfwef vfv df
       <UserLogin />
     </div>
    <BrowserRouter>
    <Navbar2 />
         <div className="fuelPass">
               <Navbar/>
               <div className="content">
                  <Routes>
                     <Route exact path="/" element={<Home />} />'
                     {/* <Route exact path="/login" element={<UserLogin />} /> */}
                     
                  </Routes>
 
               </div>
         </div>
         <div className="VehicleRegistration">
      <VehicleRegistration />
    </div>
    </BrowserRouter>
    </>
  );
}

export default App;
