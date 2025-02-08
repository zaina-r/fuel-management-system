import React, { useEffect, useState } from "react";
import Admin from "../apiservice/Admin";

const Notification = () => {
  const [stationDetails, setStationDetails] = useState([]);
  const [filteredStations, setFilteredStations] = useState([]);

  useEffect(() => {
    const fetchStations = async () => {
      try {
        const response = await Admin.getRegisterdStations();

        if (response.statusCode === 200) {
          const stations = response.stationDtosList;
          setStationDetails(stations);

        
          const filteredStations = stations.filter(
            (station) => !station.fuel || station.fuel === "N/A"
          );
          setFilteredStations(filteredStations);
          console.log(filteredStations);
        }
      } catch (err) {
        console.error("Error fetching stations:", err);
      }
    };

    fetchStations();
  }, []);

  return (
    <div className=" p-4">
      <h1 className="text-2xl font-bold text-white">New Registered Stations</h1>
      <ul className="mt-4 text-white">
        {filteredStations.length > 0 ? (
          filteredStations.map((station) => (
            <li key={station.id} className="mb-2">
              Station ID: {station.id}
            </li>
          ))
        ) : (
          <li>No stations found without fuel.</li>
        )}
      </ul>
    </div>
  );
};

export default Notification;
