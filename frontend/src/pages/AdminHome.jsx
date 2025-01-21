import React, { useEffect, useState } from "react";
import axios from "axios";

const AdminHome = () => {
  const [stations, setStations] = useState([]);
  const [isError, setIsError] = useState(false);
  const [fuelData, setFuelData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Fetch stations and their associated fuel data
        const response = await axios.get("http://localhost:8080/api/admin/stationInfo");
        setStations(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching data:", error);
        setIsError(true);
      }
    };

    fetchData();
  }, []);

  if (isError) {
    return (
      <h2 className="text-center" style={{ padding: "10rem" }}>
        Something went wrong...
      </h2>
    );
  }

  useEffect(() => {
    const fetchFuelData = async () => {
      try {
        const response2 = await axios.get(
          "http://localhost:8080/api/admin/getFuelQuantities"
        );
        setFuelData(response2.data);
      } catch (error) {
        console.error("Error fetching fuel data:", error);
        setIsError(true);
      }
    };

    fetchFuelData();
  }, []);

  if (isError) {
    return (
      <h2 className="text-center" style={{ padding: "10rem" }}>
        Something went wrong...
      </h2>
    );
  }

  return (
    <>
      <div className="grid">
        {stations.map((station) => (
          <div
            className="card mb-3"
            key={station.stationId}
            style={{
              width: "300px",
              height: "auto",
              boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
              borderRadius: "10px",
              overflow: "hidden",
              display: "flex",
              flexDirection: "column",
              justifyContent: "flex-start",
              alignItems: "stretch",
              margin: "10px",
              padding: "15px",
            }}
          >
            <div>
              <h5
                className="card-title"
                style={{ margin: "0 0 10px 0", fontSize: "1.2rem" }}
              >
                {station.dealerName.toUpperCase()}
              </h5>
              <h6
                style={{
                  margin: "0 0 10px 0",
                  fontSize: "1rem",
                  color: "gray",
                }}
              >
                Station ID: {station.stationId}
              </h6>
            </div>
            <hr className="hr-line" style={{ margin: "10px 0" }} />
            <div>
              <h6
                style={{
                  margin: "0 0 10px 0",
                  fontSize: "1.1rem",
                  fontWeight: "bold",
                }}
              >
                Available Fuel Quantities:
              </h6>
              {fuelData.map((fuel) => (
              <ul style={{ listStyleType: "none", padding: "0" }}>
                <li>Diesel: {fuel.availableDiselQuantiy} L</li>
                <li>Petrol: {fuel.availablePetrolQuantiy} L</li>
              </ul>))}
            </div>
          </div>
        ))}
      </div>
    </>
  );
};

export default AdminHome;
