import React, { useEffect, useState } from "react";
import Error from "../responseDisplay/Error";
import Success from "../responseDisplay/Success";
import StationAccountApi from "../apiservice/StationAccountApi";

// Loading spinner component
const LoadingSpinner = () => (
  <div className="flex justify-center items-center h-[300px]">
    <div className="w-12 h-12 border-4 border-t-4 border-blue-500 border-solid rounded-full animate-spin"></div>
  </div>
);

function DisplayStationDetails() {
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [stationData, setStationData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getAllData();
  }, []);

  const getAllData = async () => {
    try {
      console.log("Fetching data...");
      const timeout = setTimeout(() => {
        setLoading(false);
      }, 10000);

      const response = await StationAccountApi.getStationDetails();
      console.log("API Response:", response); // Log the full response to inspect its structure

      if (response) {
        setStationData(response.stationDto);
        setLoading(false);
      } else {
        setError("Invalid data structure received.");
        setLoading(false);
      }
    } catch (err) {
      setError(err.message || "An unexpected error occurred.");
      setSuccess("");
      setLoading(false);
    }
  };

  return (
    <>
      <div className="bg-slate-800 h-44 "></div>
      {error && <Error error={error} setError={setError} />}
      {success && <Success success={success} setSuccess={setSuccess} />}
      <div className="container my-24">
        {loading ? (
          <LoadingSpinner />
        ) : (
          <div className="bg-gray-800 rounded-xl my-10">
            <div className="flex justify-center h-[300px] text-white">
              <div className="w-3/4 p-20">
                <div className="grid grid-cols-2 p-2">
                  <div>
                    <span>Station ID:</span>
                    <span>{stationData.stationId || "N/A"}</span>
                  </div>
                  <div>
                    <span>Station Address:</span>
                    <span>{stationData.stationAddress || "N/A"}</span>
                  </div>
                </div>
                <div className="grid grid-cols-2 p-2">
                  <div>
                    <span>Dealer Name:</span>
                    <span>{stationData.dealerName || "N/A"}</span>
                  </div>
                  <div>
                    <span>License Number:</span>
                    <span>{stationData.licenseNumber || "N/A"}</span>
                  </div>
                </div>
                <div className="grid grid-cols-2 p-2">
                  <div>
                    <span>Available Petrol Capacity:</span>
                    <span>
                      {stationData.fuel?.availablePetrolQuantity || 0}
                    </span>
                  </div>
                  <div>
                    <span>Available Diesel Capacity:</span>
                    <span>{stationData.fuel?.availableDiselQuantity || 0}</span>
                  </div>
                </div>
                <div className="grid grid-cols-2 p-2">
                  <div>
                    <span>Login Code:</span>
                    <span>{stationData.loginCode || "N/A"}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
    </>
  );
}

export default DisplayStationDetails;
