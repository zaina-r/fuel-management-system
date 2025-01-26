import React, { useEffect,useRef } from "react";
import { QRCodeSVG } from "qrcode.react";
import VehicleApi from "../apiservice/VehicleApi";
import Error from "../responseDisplay/Error";
import Success from "../responseDisplay/Success";
import { useState } from "react";
import { toPng } from "html-to-image";
import { motion } from "framer-motion";
import { SlideRight } from "../animation/direction";
import { SlideUp } from "../animation/direction";


const DisplayVehicleDetails = () => {
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [qrVehicleData, setQrVehicleData] = useState([]);
  const [qrData, setQrData] = useState();




  const qrRef = useRef(null);


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

   const downloadQR = () => {
      if (qrRef.current) {
        toPng(qrRef.current, { cacheBust: true })
          .then((dataUrl) => {
            const link = document.createElement("a");
            link.download = "vehicle-qr-code.png";
            link.href = dataUrl;
            link.click();
          })
          .catch((err) => {
            console.error("Error generating image:", err);
          });
      }
    };
  

  return (
    <>
      <div className="bg-slate-800 h-screen w-full fixed ">
      {error && <Error error={error} setError={setError} />}
      {success && <Success success={success} setSuccess={setSuccess} />}
      <div className="container my-24">
        {qrVehicleData.map((vehicle, index) => (
          <motion.div
          variants={SlideUp((index+1)*0.3)}
          initial="hidden"
          whileInView={"visible"}
          
          className="text-sm rounded-xl my-5" key={index}>
            <div className="flex justify-center  h-[300px] text-white">
              <div className="w-1/4  p-7 flex justify-center items-center">
                {/* <QRCodeSVG value={vehicle.qrCode} size={150} /> */}
                <div
                  ref={qrRef}
                  style={{
                    display: "inline-block",
                    padding: "5px 25px",
                    border: "1px solid #ccc",
                    // borderRadius: "10px",
                    backgroundColor: "#fff",
                  }}
                >
                  <div
                    style={{
                      marginBottom: "",
                      fontSize: "18px",
                      fontWeight: "bold",
                      textAlign: "center",
                      color:"black",
                      
                    }}
                  >
                    {vehicle.license_plate_no}
                  </div>
                  <QRCodeSVG value={vehicle.qrCode} size={130} />
                  <div
                    style={{
                      marginTop: "",
                      fontSize: "16px",
                      color: "#555",
                      textAlign: "center",
                    }}
                  >
                    {vehicle.qrCode}
                  </div>
                </div>
                  
              </div>  
              <div className="w-3/4 p-20">
                <div className="grid grid-cols-2 p-2">
                  <div> 
                    <span className="text-orange-400">Vehicle Number:</span>
                    <span>{vehicle.license_plate_no}</span> 
                  </div>
                  <div>
                    <span className="text-orange-400">Chassis Number:</span>
                    <span>{vehicle.vehicleRegNo}</span>
                  </div>
                </div>
                <div className="grid grid-cols-2 p-2">
                  <div>
                    <span className="text-orange-400">VehicleType:</span>
                    <span>{vehicle.vehicle_type}</span>
                  </div>
                  <div>
                    <span className="text-orange-400">FuelType:</span>
                    <span>{vehicle.fuel_type}</span>
                  </div>
                </div>
                <div className="grid grid-cols-2 p-2">
                  <div>
                    <span className="text-orange-400">Maximum Fuel Capacity:</span>
                    <span>{vehicle.maximumFuelCapacity}</span>
                  </div>
                  <div>
                    <span className="text-orange-400">Available Fuel Capacity:</span>
                    <span>{vehicle.availableFuelCapacity}</span>
                  </div>
                </div>
                <div className="grid grid-cols-2 p-2">
                  <div>
                    <span className="text-orange-400">QRCode:</span>
                    <span>{vehicle.qrCode}</span>
                  </div>
                  <div>
                    <button className="bg-green-600 w-[200px] py-1" onClick={downloadQR}>
                      download
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </motion.div>
        ))}
      </div>
      </div>
     
    </>
  );
};

export default DisplayVehicleDetails;
