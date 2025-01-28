import React, { useEffect, useState } from "react";
import Admin from "../apiservice/Admin";

const ViewStations = () => {
  const [stationDetails, setStationDetails] = useState([]);
  // const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch data from the API
  useEffect(() => {
    const fetchStations = async () => {
      try {
        const response = await Admin.getStations(); // Replace with your API endpoint
        if (response.statusCode == 200) {
          console.log(response.stationWithStatusDTOList);
          setStationDetails(response.stationWithStatusDTOList);
        }
      } catch (err) {
        setError(err.message);
      } finally {
        // setLoading(false);
      }
    };

    fetchStations();
  }, []);

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4 text-white">Station Dashboard</h1>
      <div className="overflow-x-auto">
        <table className="table-auto w-full text-white">
          <thead className="text-left">
            <tr className="">
              <th className="text-orange-500 px-4 py-2">ID</th>
              <th className="text-orange-500 px-4 py-2">DealerId</th>
              <th className="text-orange-500 px-4 py-2">DealerName</th>
              <th className="text-orange-500 px-4 py-2">status</th>
            </tr>
          </thead>
          <tbody>
            {stationDetails.map((station, index) => (
              <tr key={index} className="border-b-2 border-neutral-500">
                <td className=" px-4 py-9 text-neutral-400 ">{station.id}</td>
                <td className=" px-4 py-2 text-neutral-400">{station.dealerId}</td>
                <td className=" px-4 py-2 text-neutral-400 ">{station.dealerName}</td>
                <td>
                  <button
                    className={`px-4 py-2 w-[150px] rounded-lg ${
                      station.status === "Registered"
                        ? "bg-green-600"
                        : station.status === "Not Registered"
                        ? "bg-red-600"
                        : ""
                    }`}
                  >
                    {station.status}
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ViewStations;
