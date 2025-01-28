import React, { useEffect, useState } from 'react';

const ViewAdmin = () => {
  const [adminDetails, setAdminDetails] = useState([]);
  // const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch data from the API
  useEffect(() => {
    // const fetchAdmins = async () => {
    //   try {
    //     const response = await fetch('/api/admins'); // Replace with your actual API endpoint
    //     if (!response.ok) {
    //       throw new Error('Failed to fetch admin details');
    //     }
    //     const data = await response.json();
    //     // Filter the data based on the role "FUELSTATION_OWNER"
    //     const filteredData = data.filter((admin) => admin.role === 'FUELSTATION_OWNER');
    //     setAdminDetails(filteredData);
    //   } catch (err) {
    //     setError(err.message);
    //   } finally {
    //     setLoading(false);
    //   }
    // };

    // fetchAdmins();
  }, []);

  // if (loading) return <div>Loading...</div>;
  // if (error) return <div>Error: {error}</div>;

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4 text-color">Admin Dashboard</h1>
      <div className="overflow-x-auto">
        <table className="table-auto w-full ">
          <thead className='text-left'>
            <tr className="">
              <th className=" px-4 py-2 text-orange-500">ID</th>
              <th className=" px-4 py-2 text-orange-500 ">NIC</th>
              <th className=" px-4 py-2 text-orange-500">First Name</th>
              <th className=" px-4 py-2 text-orange-500">Last Name</th>
              <th className=" px-4 py-2 text-orange-500">Role</th>
              <th className=" px-4 py-2 text-orange-500">Telephone Number</th>
            </tr>
          </thead>
          <tbody>
            {adminDetails.map((admin, index) => (
              <tr key={index} className="">
                <td className=" px-4 py-2">{admin.id}</td>
                <td className=" px-4 py-2">{admin.nic}</td>
                <td className=" px-4 py-2">{admin.firstName}</td>
                <td className=" px-4 py-2">{admin.lastName}</td>
                <td className=" px-4 py-2">{admin.role}</td>
                <td className=" px-4 py-2">{admin.telNo}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ViewAdmin;
