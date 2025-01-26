import React, { useEffect, useState } from "react";
import axios from "axios";

const StationTable = () => {
  const [stationData, setStationData] = useState([]);
  const [isError, setIsError] = useState(false);

  useEffect(() => {
    const fetchStationData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/admin/stationDetails");
        setStationData(response.data);
      } catch (error) {
        console.error("Error fetching station data:", error);
        setIsError(true);
      }
    };

    fetchStationData();
  }, []);

  if (isError) {
    return <h2>Failed to load data. Please try again later.</h2>;
  }

  return (
    <div>
      <h1>Fuel Station Details</h1>
      <table border="1" style={{ width: "100%", textAlign: "left" }}>
        <thead>
          <tr>
            <th>Station ID</th>
            <th>Station Address</th>
            <th>Dealer Name</th>
            <th>License Number</th>
            <th>Registration Date</th>
            <th>Available Diesel Quantity</th>
            <th>Available Petrol Quantity</th>
          </tr>
        </thead>
        <tbody>
          {stationData.map((station, index) => (
            <tr key={index}>
              <td>{station.stationId}</td>
              <td>{station.stationAddress}</td>
              <td>{station.dealerName}</td>
              <td>{station.licenseNumber}</td>
              <td>{station.registrationDate}</td>
              <td>{station.availableDiselQuantity || "N/A"}</td>
              <td>{station.availablePetrolQuantity || "N/A"}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default StationTable;
