import React, { useState, useEffect } from "react";
import axios from "axios";
import Admin  from "../apiservice/Admin";


const ViewRegisteredVehicle = () => {
  const [vehicles, setVehicles] = useState([]);
  // const [loading, setLoading] = useState(true);

  useEffect(() => {
    
    const fetchVehicles = async () => {
      try {
        const response = await Admin.getRegisterdVehicles()
        if(response.statusCode==200){
          console.log(response.vehiclesDtoList);
          setVehicles(response.vehiclesDtoList);
        }
      
        
      } catch (error) {
        console.error('Error fetching vehicles:', error);
       
      }
    };
    fetchVehicles();
  }, []);

  return (
    <div className="p-4 text-white">
      <h1 className="text-xl font-bold mb-4">Registered Vehicles</h1>

      <table className="table-auto   w-full">
        <thead>
          <tr className="text-orange-400">
            <th className=" px-4 py-2 text-left">Vehicle ID</th>
            <th className=" px-4 py-2 text-left">License Plate</th>
            <th className=" px-4 py-2 text-left">Registration No</th>
            <th className=" px-4 py-2 text-left">Vehicle Type</th>
            <th className=" px-4 py-2 text-left">Fuel Type</th>
          </tr>
        </thead>
        <tbody>
          {vehicles.map((vehicle) => (
            <tr key={vehicle.vehicleId} className=" border-b-2 border-neutral-500">
              <td className=" px-4 py-6 text-neutral-400">{vehicle.vehicleId}</td>
              <td className=" px-4 py-2 text-neutral-400">{vehicle.license_plate_no}</td>
              <td className="  px-4 py-2 text-neutral-400">{vehicle.vehicleRegNo}</td>
              <td className=" px-4 py-2 text-neutral-400">{vehicle.vehicle_type}</td>
              <td className="  px-4 py-2 text-neutral-400">{vehicle.fuel_type}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ViewRegisteredVehicle;
