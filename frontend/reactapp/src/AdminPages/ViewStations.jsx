import React, { useEffect, useState } from 'react';

const ViewStations = () => {
  const [stationDetails, setStationDetails] = useState([]);
  // const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch data from the API
  useEffect(() => {
    // const fetchStations = async () => {
    //   try {
    //    const response = await fetch('/api/stations'); // Replace with your API endpoint
    //     if (!response.ok) {
    //       throw new Error('Failed to fetch station details');
    //     }
    //     const data = await response.json();
    //     setStationDetails(data);
    //   } catch (err) {
    //     setError(err.message);
    //   } finally {
    //     setLoading(false);
    //   }
    // };

    // fetchStations();
  }, []);

  // if (loading) return <div>Loading...</div>;
  // if (error) return <div>Error: {error}</div>;

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4 text-white">Station Dashboard</h1>
      <div className="overflow-x-auto">
        <table className="table-auto w-full text-white">
          <thead>
            <tr className="">
              <th className=" px-4 py-2">ID</th>
              <th className=" px-4 py-2">Dealer Name</th>
              <th className=" px-4 py-2">License Number</th>
              <th className=" px-4 py-2">Registration Date</th>
              <th className="px-4 py-2">Station Address</th>
              <th className=" px-4 py-2">Station ID</th>
            </tr>
          </thead>
          <tbody>
            {stationDetails.map((station, index) => (
              <tr key={index} className="odd:bg-white even:bg-gray-100">
                <td className="border border-gray-300 px-4 py-2">{station.id}</td>
                <td className="border border-gray-300 px-4 py-2">{station.dealerName}</td>
                <td className="border border-gray-300 px-4 py-2">{station.licenseNumber}</td>
                <td className="border border-gray-300 px-4 py-2">{station.registrationDate}</td>
                <td className="border border-gray-300 px-4 py-2">{station.stationAddress}</td>
                <td className="border border-gray-300 px-4 py-2">{station.stationId}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ViewStations;

