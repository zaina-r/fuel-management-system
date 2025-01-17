import { useState } from "react";
import "./App.css";
import UserLogin from "./pages/UserLogin";
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';

import Navbar from './components/Navbar';


function App() {
  return (
    // <div>
    //   <UserLogin />
    // </div>
    <BrowserRouter>
         <div className="fuelPass">
               <Navbar/>
               <div className="content">
                  <Routes>
                     {/* <Route path="/" element={<UserLogin />} /> */}
                  </Routes>
 
               </div>
         </div>
    
    </BrowserRouter>
  );
}

export default App;
