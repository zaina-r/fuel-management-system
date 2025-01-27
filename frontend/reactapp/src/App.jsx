import React from "react"
import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom"
import Navbar from "./components/Navbar"
import AdminNavbar from "./components/AdminNavbar"
import Home from "./pages/Home"
import About from "./pages/About"
import UserLogin from "./pages/UserLogin"
import UserRegisterationForm from "./pages/UserRegisterationForm"
import VehicleRegistration from "./pages/VehicleRegistration"
import DisplayVehicleDetails from "./pages/DisplayVehicleDetails"
import ForgotPassword from "./pages/Forgotpassword"
import StationRegistration from "./pages/StationRegistration"
import DisplayStationDetails from "./pages/DisplayStationDetails"
import Contact from "./pages/Contact"
import AdminDashboard from "./AdminPages/AdminDashboard"
import ManageReports from "./AdminPages/ManageReports"
import AdminHome from "./AdminPages/AdminHome"
import ViewStations from "./AdminPages/ViewStations"
import UpdateStationFuel from "./AdminPages/UpdateStationFuel"
import UpdateStation from "./AdminPages/UpdateStation"
import ViewRegisteredVehicle from "./AdminPages/ViewRegisteredVehicle"
import ViewAdmins from "./AdminPages/ViewAdmin"
import ViewStationOwners from "./AdminPages/ViewStationOwners"
import ViewProfile from "./AdminPages/ViewProfile"
import Notification from "./AdminPages/Notification"

function App() {
  return (
    <BrowserRouter>
      <RoutesWrapper />
    </BrowserRouter>
  )
}

function RoutesWrapper() {
  const location = useLocation()

  
  const isAdminRoute = location.pathname.startsWith('/admin')

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

          {/* Admin routes */}
          <Route path="/admin" element={<AdminDashboard />}>
            <Route index element={<AdminHome />} />
            
            <Route path="reports" element={<ManageReports />} />
            <Route path="viewStations" element={<ViewStations />} />
            <Route path="updateStationFuel" element={<UpdateStationFuel />} />
            <Route path="updateStationFuel" element={<UpdateStationFuel />} />
            <Route path="updateStation" element={<UpdateStation />} />
            <Route path="registeredVehicles" element={<ViewRegisteredVehicle/>} />
            <Route path="admins" element={<ViewAdmins/>} />
            <Route path="stationOwners" element={<ViewStationOwners/>} />
            <Route path="profile" element={<ViewProfile/>} />
            <Route path="notification" element={<Notification/>} />
            





            

            


          </Route>
        </Routes>
      </div>

      
      {/* {isAdminRoute && <AdminNavbar />} */}
    </div>
  )
}

export default App
