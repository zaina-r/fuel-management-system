import React, { useEffect, useState } from "react";
import axios from "axios";

// Admin service for fetching station reports
const Admin = {
  getStationReports: async () => {
    return axios.get("http://localhost:8080/api/fuelAllocation/alltransitions");
  },
};

const FuelAllocationTable = () => {
  const [data, setData] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true); // Add loading state

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      setLoading(true); // Start loading
      const response = await Admin.getStationReports();

      // Verify the response and extract the data
      if (response.status === 200 && response.data?.fuelTransitionDtoList) {
        setData(response.data.fuelTransitionDtoList);
      } else {
        throw new Error("Invalid response format");
      }
      setError(""); // Clear any previous errors
    } catch (err) {
      setError("Failed to fetch data. Please try again later.");
      console.error("Fetch data error:", err);
    } finally {
      setLoading(false); // Stop loading
    }
  };

  return (
    <div className="container mx-auto p-5 text-white">
      <h1 className="text-2xl font-bold mb-4">Fuel Allocations</h1>

      {loading && <p className="text-orange-500">Loading data...</p>}
      {error && <p className="text-red-500">{error}</p>}
      {!loading && data.length === 0 && !error && (
        <p className="text-gray-400">No records found.</p>
      )}

      {!loading && data.length > 0 && (
        <table className="table-auto w-full ">
          <thead className="text-orange-500">
            <tr className="text-left">
              <th className="  px-4 py-2">ID</th>
              <th className="  px-4 py-2">Station ID</th>
              <th className=" px-4 py-2">Fuel Type</th>
              <th className=" px-4 py-2">Fuel Amount</th>
              <th className=" py-2">Transition Time</th>
              <th className=" px-4 py-2">Vehicle ID</th>
              <th className=" px-4 py-2">User ID</th>
            </tr>
          </thead>
          <tbody>
            {data.map((item) => (
              <tr key={item.id} className="border-b border-neutral-500">
                <td className=" px-4 py-6">{item.id}</td>
                <td className=" px-4 py-2">{item.stationId}</td>
                <td className=" px-4 py-2">{item.fuelType}</td>
                <td className=" px-4 py-2">
                  {item.fuelAmount?.toFixed(2) || "0.00"}
                </td>
                <td className=" px-4 py-2">
                  {item.transitionTime
                    ? new Date(item.transitionTime).toLocaleString()
                    : "N/A"}
                </td>
                <td className=" px-4 py-2">
                  {item.vehicleVerification?.license_plate_no || "N/A"}
                </td>
                <td className=" px-4 py-2">
                  {item.userAccount?.username || "N/A"}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default FuelAllocationTable;
