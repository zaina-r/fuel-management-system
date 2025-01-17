import React, { useEffect, useState } from "react";
import axios from "axios";

const AdminHome = () => {
  const [stations, setStations] = useState([]);
  const [isError, setIsError] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
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

  return (
    <>
      <div className="grid">
        {stations.map((station) => (
          <div
            className="card mb-3"
            key={station.id}
            style={{
              width: "270px",
              height: "210px",
              boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
              borderRadius: "10px",
              overflow: "hidden",

              display: "flex",
              flexDirection: "column",
              justifyContent: "flex-start",
              alignItems: "stretch",
            }}
          >
            <div
              className="card-body"
              style={{
                flexGrow: 1,
                display: "flex",
                flexDirection: "column",
                justifyContent: "space-between",
                padding: "10px",
              }}
            >
              <div>
                <h5
                  className="card-title"
                  style={{ margin: "0 0 10px 0", fontSize: "1.2rem" }}
                >
                  {station.dealerName.toUpperCase()}
                </h5>
                
              </div>
              <hr className="hr-line" style={{ margin: "10px 0" }} />
              <div>
                <h5
                  className="card-body"
                  style={{ margin: "0 0 10px 0", fontSize: "1.2rem" }}
                >
                  {station.licenseNumber}
                </h5>
                </div>
              <hr className="hr-line" style={{ margin: "10px 0" }} />
              <div>
                
              </div>
              
            </div>
          </div>
        ))}
      </div>
    </>
  );
};

export default AdminHome;
