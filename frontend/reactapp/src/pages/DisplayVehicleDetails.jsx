import React, { useEffect } from "react";
import { QRCodeSVG } from "qrcode.react";
import VehicleApi from "../apiservice/VehicleApi";
import Error from "../responseDisplay/Error";
import Success from "../responseDisplay/Success";
import { useState } from "react";

const DisplayVehicleDetails = () => {
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [qrVehicleData, setQrVehicleData] = useState([]);

  useEffect(() => {
    getAllQr();
  }, []);

  const getAllQr = async () => {
    try {
      const response = await VehicleApi.getVehicleDetails();

      setQrVehicleData(response.vehiclesDtoList);
      console.log(response.vehiclesDtoList);

      console.log(qrVehicleData);

      setError("");
    } catch (err) {
      setError(err.message || "An unexpected error occurred.");
      setSuccess("");
    }
  };

  return (
    <>
      <div className="bg-slate-800 h-44 "></div>
      {error && <Error error={error} setError={setError} />}
      {success && <Success success={success} setSuccess={setSuccess} />}
      <div className="container my-24">
        {qrVehicleData.map((vehicle, index) => (
          <div className="bg-gray-800 rounded-xl my-10" key={index}>
            <div className="flex justify-center  h-[300px] text-white">
              <div className="w-1/4  p-7">
                <QRCodeSVG value={vehicle.qrCode} size={230} />
              </div>
              <div className="w-3/4 p-20">
                <div className="grid grid-cols-2 p-2">
                  <div>
                    <span>Vehicle Number:</span>
                    <span>{vehicle.license_plate_no}</span>
                  </div>
                  <div>
                    <span>Chassis Number:</span>
                    <span>{vehicle.vehicleRegNo}</span>
                  </div>
                </div>
                <div className="grid grid-cols-2 p-2">
                  <div>
                    <span>VehicleType:</span>
                    <span>{vehicle.vehicle_type}</span>
                  </div>
                  <div>
                    <span>FuelType:</span>
                    <span>{vehicle.fuel_type}</span>
                  </div>
                </div>
                <div className="grid grid-cols-2 p-2">
                  <div>
                    <span>Maximum Fuel Capacity:</span>
                    <span>{vehicle.maximumFuelCapacity}</span>
                  </div>
                  <div>
                    <span>Available Fuel Capacity:</span>
                    <span>{vehicle.availableFuelCapacity}</span>
                  </div>
                </div>
                <div className="grid grid-cols-2 p-2">
                  <div>
                    <span>QRCode:</span>
                    <span>{vehicle.qrCode}</span>
                  </div>
                  <div>
                    <button className="bg-green-600 w-full py-1">
                      download
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </>
  );
};

export default DisplayVehicleDetails;
