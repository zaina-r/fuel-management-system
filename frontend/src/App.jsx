import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AdminNavbar from "./Components/AdminNavbar";
import AdminHome from "./pages/AdminHome";
import AdminAddFuelStation from "./pages/AdminAddFuelStation";

const App = () => {
  return (
    <Router>
      <AdminNavbar />
      <Routes>
        <Route path="/" element={<AdminHome />} />
        <Route path="/add-fuel-station" element={<AdminAddFuelStation />} />
      </Routes>
    </Router>
  );
};

export default App;
