import React from "react";
import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom";
import Navbar from "./components/Navbar";
import AdminNavbar from "./components/AdminNavbar";
import Home from "./pages/Home";
import About from "./pages/About";
import UserLogin from "./pages/UserLogin";
import UserRegisterationForm from "./pages/UserRegisterationForm";
import VehicleRegistration from "./pages/VehicleRegistration";
import DisplayVehicleDetails from "./pages/DisplayVehicleDetails";
import ForgotPassword from "./pages/Forgotpassword";
import StationRegistration from "./pages/StationRegistration";
import DisplayStationDetails from "./pages/DisplayStationDetails";
import Contact from "./pages/Contact";
import AdminDashboard from "./AdminPages/AdminDashboard";
import ManageReports from "./AdminPages/ManageReports";
import AdminHome from "./AdminPages/AdminHome";
import ViewStations from "./AdminPages/ViewStations";
import UpdateStationFuel from "./AdminPages/UpdateStationFuel";
import UpdateStation from "./AdminPages/UpdateStation";
import ViewRegisteredVehicle from "./AdminPages/ViewRegisteredVehicle";
import ViewProfile from "./AdminPages/ViewProfile";
import Notification from "./AdminPages/Notification";
import ProfilePage from "./pages/Profile";
import UpdateSelectedStationFuel from "./AdminPages/UpdateSelectedStationFuel";
import ManageUser from "./AdminPages/ManageUser";
import FuelManage from "./AdminPages/FuelManage";
import Service from "./pages/Service";
import AvailableVehicles from "./AdminPages/AvailableVehicles";

function App() {
  return (
    <BrowserRouter>
      <RoutesWrapper />
    </BrowserRouter>
  );
}

function RoutesWrapper() {
  const location = useLocation();

  const isAdminRoute = location.pathname.startsWith("/admin");

  return (
    <div className="fuelPass">
      {!isAdminRoute && <Navbar />}

      <div className="content">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/login" element={<UserLogin />} />
          <Route path="/register" element={<UserRegisterationForm />} />
          <Route path="/vehicleRegister" element={<VehicleRegistration />} />
          <Route path="/vehicleHistory" element={<DisplayVehicleDetails />} />
          <Route path="/forgotpassword" element={<ForgotPassword />} />
          <Route path="/stationRegister" element={<StationRegistration />} />
          <Route path="/StationHistory" element={<DisplayStationDetails />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/service" element={<Service />} />

          <Route path="/profile" element={<ProfilePage />} />

          {/* Admin routes */}
          <Route path="/admin" element={<AdminDashboard />}>
            <Route index element={<AdminHome />} />

            <Route path="reports" element={<ManageReports />} />
            <Route path="viewStations" element={<ViewStations />} />
            <Route path="updateStationFuel" element={<UpdateStationFuel />} />
            <Route path="updateStationFuel" element={<UpdateStationFuel />} />
            <Route path="updateStation" element={<UpdateStation />} />
            <Route
              path="registeredVehicles"
              element={<ViewRegisteredVehicle />}
            />
             <Route
              path="AvailableVehicles"
              element={<AvailableVehicles />}
            />
            <Route path="manageuser" element={<ManageUser />} />
            <Route path="profile" element={<ViewProfile />} />
            <Route path="fuelTypes" element={<FuelManage />} />

            <Route path="notification" element={<Notification />} />
            <Route path="updateSelectedStationFuel" element={<UpdateSelectedStationFuel />} />

          </Route>
        </Routes>
      </div>

      {/* {isAdminRoute && <AdminNavbar />} */}
    </div>
  );
}

export default App;
