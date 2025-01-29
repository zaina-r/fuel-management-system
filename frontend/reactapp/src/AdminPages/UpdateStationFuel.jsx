import React, { useEffect, useState } from "react";
import Admin from "../apiservice/Admin";
import { useNavigate } from "react-router-dom";

const ViewStations = () => {
  const [stationDetails, setStationDetails] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  
  useEffect(() => {
    const fetchStations = async () => {
      try {
        const response = await Admin.getRegisterdStations(); 
        if (response.statusCode === 200) {
          console.log(response.stationDtosList);
          setStationDetails(response.stationDtosList);
        }
      } catch (err) {
        setError(err.message);
      }
    };

    fetchStations();
  }, []);

  const handleUpdate = (index) => {
    navigate("/admin/updateSelectedStationFuel", { state: { station: stationDetails[index] } });
  };

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4 text-white">Station Dashboard</h1>

      {error ? (
        <p className="text-red-500">Error: {error}</p>
      ) : (
        <div className="overflow-x-auto">
          <table className="table-auto w-full text-white">
            <thead className="text-left">
              <tr>
                <th className="text-orange-500 px-4 py-2">ID</th>
                <th className="text-orange-500 px-4 py-2">Diesel Available</th>
                <th className="text-orange-500 px-4 py-2">Petrol Available</th>
                <th className="text-orange-500 px-4 py-2">Registration Date</th>
                <th className="text-orange-500 px-4 py-2">Station Address</th>
                <th className="text-orange-500 px-4 py-2">Station ID</th>
                <th className="text-orange-500 px-4 py-2">Allocate Fuel</th>
              </tr>
            </thead>
            <tbody>
              {stationDetails.map((station, index) => (
                <tr key={index} className="border-b border-neutral-500 text-sm">
                  <td className="px-4 py-2 text-neutral-400">{station.id}</td>
                  <td className="px-4 py-2 text-neutral-400">
                    {station.fuel
                      ? station.fuel.availableDieselQuantity
                      : "N/A"}
                  </td>
                  <td className="px-4 py-6 text-neutral-400">
                    {station.fuel ? station.fuel.availablePetrolQuantity : "N/A"}
                  </td>
                  <td className="px-4 py-6 text-neutral-400">
                    {station.registrationDate}
                  </td>
                  <td className="px-4 py-6 text-neutral-400">
                    {station.stationAddress}
                  </td>
                  <td className="px-4 py-6 text-neutral-400">
                    {station.stationId}
                  </td>
                  <td>
                    <button
                      className="bg-green-600 text-white px-4 py-2 rounded"
                      onClick={() => handleUpdate(index)}
                    >
                      Update Fuel
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default ViewStations;
