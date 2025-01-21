import { useState } from "react";
import StationAccountApi from "../apiservice/StationAccountApi";

const StationRegistration = () => {
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [formData, setFormData] = useState({
    licenseNumber: "",
    stationId: "",
    dealerName: "",
    stationAddress: "",
  });

  const validateStation = () => {
    const { licenseNumber, stationId, dealerName, stationAddress } = formData;

    if (stationId && dealerName && stationAddress && licenseNumber) {
      return true;
    }
    return false;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    console.log(FormData);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateStation()) {
      setError("Please fill in all the required fields.");
      return;
    }

    try {
      const response = await StationAccountApi.registerStation(formData);
      console.log(response);

      if (response.statusCode === 200) {
        setFormData({
          licenseNumber: "",
          stationId: "",
          dealerName: "",
          stationAddress: "",
        });
        setLicense(false);
        setError("");
        setSuccess("Otp can be sent successful!");
      }
    } catch (error) {
      setSuccess("");
      setError(error.response?.data?.message || error.message);
    }
  };
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 py-12">
      <div className="w-full max-w-md bg-white p-8 rounded-lg shadow-lg">
        <h2 className="text-2xl font-bold text-center mb-6">
          Station Registration Form
        </h2>

        <form onSubmit={handleSubmit} className="space-y-4">
          {error && <p className="mt-4 text-red-500 text-center">{error}</p>}
          {success && (
            <p className="mt-4 text-green-500 text-center">{success}</p>
          )}
          <div>
            <label
              htmlFor="licenseNumber"
              className="block text-sm font-medium text-gray-700"
            >
              Licence No:
            </label>
            <input
              type="text"
              name="licenseNumber"
              value={formData.licenseNumber}
              onChange={handleChange}
              placeholder="Enter Licence No"
              className="mt-1 block w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <div>
            <label
              htmlFor="stationId"
              className="block text-sm font-medium text-gray-700"
            >
              Station ID:
            </label>
            <input
              type="text"
              name="stationId"
              value={formData.stationId}
              onChange={handleChange}
              placeholder="10XXXX"
              className="mt-1 block w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <div>
            <label
              htmlFor="dealerName"
              className="block text-sm font-medium text-gray-700"
            >
              Dealer Name:
            </label>
            <input
              type="text"
              name="dealerName"
              value={formData.dealerName}
              onChange={handleChange}
              placeholder="Enter Dealer's Name"
              className="mt-1 block w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <div>
            <label
              htmlFor="stationAddress"
              className="block text-sm font-medium text-gray-700"
            >
              Station Address:
            </label>
            <input
              type="text"
              name="stationAddress"
              value={formData.stationAddress}
              onChange={handleChange}
              placeholder="Station Address"
              className="mt-1 block w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <div className="mt-6">
            <button
              type="submit"
              className="w-full bg-blue-500 text-white py-3 rounded-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400"
            >
              Submit
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default StationRegistration;
