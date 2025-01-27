import React, { useEffect, useState } from 'react';
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
        if (response.statusCode==200) {
          console.log(response.stationDtosList)
        setStationDetails(response.stationDtosList);
            
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
          <thead className='text-left'>
            <tr className="">
              <th className="text-orange-500 px-4 py-2">ID</th>
              <th className="text-orange-500 px-4 py-2">Dealer Name</th>
              <th className="text-orange-500 px-4 py-2">License Number</th>
              <th className="text-orange-500 px-4 py-2">Registration Date</th>
              <th className="text-orange-500 px-4 py-2">Station Address</th>
              <th className="text-orange-500 px-4 py-2">Station ID</th>
            </tr>
          </thead>
          <tbody>
            {stationDetails.map((station, index) => (
              <tr key={index} className="">
                <td className=" px-4 py-2">{station.id}</td>
                <td className=" px-4 py-2">{station.dealerName}</td>
                <td className=" px-4 py-2">{station.licenseNumber}</td>
                <td className=" px-4 py-2">{station.registrationDate}</td>
                <td className=" px-4 py-2">{station.stationAddress}</td>
                <td className=" px-4 py-2">{station.stationId}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ViewStations;

