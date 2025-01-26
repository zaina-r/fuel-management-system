import { useState } from "react";
import StationAccountApi from "../apiservice/StationAccountApi";
import Error from "../responseDisplay/Error";
import Success from "../responseDisplay/Success";
import { Oval } from "react-loader-spinner";
import { motion } from "framer-motion";
import { SlideRight } from "../animation/direction";
import { SlideLeft } from "../animation/direction";

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
      <div className="bg-slate-800 h-screen w-full fixed ">
        {error && <Error error={error} setError={setError} />}
        {success && <Success success={success} setSuccess={setSuccess} />}
        <div className=" container text-sm mt-20">
          <div className="flex items-center justify-center  py-12  ">
            <motion.div 
               variants={SlideRight(0.1)}
               initial="hidden"
               whileInView={"visible"}
            className="w-full max-w-md  p-5 rounded-lg ">
              <h2 className="text-4xl font-bold text-white  mb-6">
                Station Registration
              </h2>

              <form onSubmit={handleSubmit} className="space-y-6">
                <div className="flex items-center space-x-4">
                  <div>
                    <label
                      htmlFor="licenseNumber"
                      className="block text-sm font-medium text-neutral-400  w-[200px] mb-1"
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
                  <div>
                    <label
                      htmlFor="stationId"
                      className="block text-sm font-medium text-neutral-400 mb-1"
                    >
                      Station ID
                    </label>
                    <input
                      type="text"
                      name="stationId"
                      value={formData.stationId}
                      onChange={handleChange}
                      placeholder="10XXXX"
                      className="bg-gray-200 p-1 rounded-sm text-md w-[193px] "
                    />
                  </div>
                </div>

                <div className="space-y-2">
                  <label
                    htmlFor="dealerName"
                    className="block text-sm font-medium text-neutral-400"
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
                    className="block text-sm font-medium text-neutral-400"
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
                    className={`bg-blue-800 w-full text-white p-2 my-10 flex items-center justify-center  ${
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
            </motion.div>
            <motion.div
             variants={SlideLeft(0.1)}
             initial="hidden"
             whileInView={"visible"}
             className="w-1/2 p-10 mb-24">
                <img src="src/Assets/like-removebg-preview.png" alt=""  />
            </motion.div>
          </div>
        </div>
      </div>
    </>
  );
};

export default StationRegistration;
