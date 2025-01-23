import { useState } from "react";
import StationAccountApi from "../apiservice/StationAccountApi";
import Error from "../responseDisplay/Error";
import Success from "../responseDisplay/Success";
import { Oval } from "react-loader-spinner";

const StationRegistration = () => {
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [loading, setLoading] = useState(false);
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
    setLoading(true);

    try {
      const response = await StationAccountApi.registerStation(formData);

      console.log(response.data);
      if (response.statusCode === 200) {
        const id = response.stationDto.id;
        localStorage.setItem("stationId", id);
        setFormData({
          licenseNumber: "",
          stationId: "",
          dealerName: "",
          stationAddress: "",
        });

        setError("");
        setSuccess("Successfully registered Otp can be sentfull!");
        setTimeout(() => setSuccess(""), 5000);
      } else {
        setError(response?.message || error.message);
        setTimeout(() => setError(""), 5000);
      }
    } catch (error) {
      setSuccess("");
      setError(error.response?.data?.message || error.message);
      setTimeout(() => setError(""), 5000);
    } finally {
      setLoading(false);
    }
  };
  return (
    <>
      <div className="bg-slate-800 h-44 "></div>
      {error && <Error error={error} setError={setError} />}
      {success && <Success success={success} setSuccess={setSuccess} />}
      <div className=" container text-sm ">
        <div className="flex items-center justify-center  py-12 w-1/2 ">
          <div className="w-full max-w-md bg-white p-5 rounded-lg shadow-lg">
            <h2 className="text-4xl font-bold text-neutral-800  mb-6">
              Station Registration
            </h2>

            <form onSubmit={handleSubmit} className="space-y-7">
              <div className="space-y-2">
                <label
                  htmlFor="licenseNumber"
                  className="block text-sm font-medium text-gray-700 "
                >
                  Licence No
                </label>
                <input
                  type="text"
                  name="licenseNumber"
                  value={formData.licenseNumber}
                  onChange={handleChange}
                  placeholder="Enter Licence No"
                  className="bg-gray-200 p-1 rounded-sm text-md w-full  "
                />
              </div>

              <div className="space-y-2">
                <label
                  htmlFor="stationId"
                  className="block text-sm font-medium text-gray-700"
                >
                  Station ID
                </label>
                <input
                  type="text"
                  name="stationId"
                  value={formData.stationId}
                  onChange={handleChange}
                  placeholder="10XXXX"
                  className="bg-gray-200 p-1 rounded-sm text-md w-full"
                />
              </div>

              <div className="space-y-2">
                <label
                  htmlFor="dealerName"
                  className="block text-sm font-medium text-gray-700"
                >
                  Dealer Name
                </label>
                <input
                  type="text"
                  name="dealerName"
                  value={formData.dealerName}
                  onChange={handleChange}
                  placeholder="Enter Dealer's Name"
                  className="bg-gray-200 p-1 rounded-sm text-md w-full"
                />
              </div>

              <div className="space-y-2">
                <label
                  htmlFor="stationAddress"
                  className="block text-sm font-medium text-gray-700"
                >
                  Station Address
                </label>
                <input
                  type="text"
                  name="stationAddress"
                  value={formData.stationAddress}
                  onChange={handleChange}
                  placeholder="Station Address"
                  className="bg-gray-200 p-1 rounded-sm text-md w-full"
                />
              </div>

              <div className="mb-6 ">
                <button
                  type="submit"
                  onClick={handleSubmit}
                  className={`bg-blue-800 w-full text-white p-2 flex items-center justify-center ${
                    loading
                      ? "opacity-50 cursor-not-allowed"
                      : "hover:bg-blue-600"
                  }`}
                  disabled={loading}
                >
                  {loading ? (
                    <Oval
                      height={24}
                      width={24}
                      color="white"
                      visible={true}
                      ariaLabel="oval-loading"
                      secondaryColor="white"
                      strokeWidth={3}
                      strokeWidthSecondary={3}
                    />
                  ) : (
                    "Station Register"
                  )}
                </button>
              </div>
            </form>
          </div>
          <div className="w-1/2 bg-red-800 h-4">
            {/* <img src="src/Assets/photo-1697575806262-c3553b142b54.avif" alt=""  className="w-[400px] h-[400px]"/> */}
          </div>
        </div>
      </div>
    </>
  );
};

export default StationRegistration;
