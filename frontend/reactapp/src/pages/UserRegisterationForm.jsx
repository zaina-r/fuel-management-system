import React, { useState } from "react";
import UserAccountApi from "../apiservice/UserAccountApi";
import Error from "../responseDisplay/Error";
import Success from "../responseDisplay/Success";
import { motion } from "framer-motion";
import { SlideUp } from "../animation/direction";
import { SlideLeft } from "../animation/direction";
import { Oval } from "react-loader-spinner";

const UserRegisterationForm = () => {
  const [license, setLicense] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [otpSent, setOtpSent] = useState(false);
  const [otpVerified, setOtpVerified] = useState(false);
  const [otp, setOtp] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const [formData, setFormData] = useState({
    firstname: "",
    lastname: "",
    username: "",
    password: "",
    nic: "",
    telno: "",
    role: "",
    licenseNumber: "",
  });

  const validateForm = () => {
    const {
      firstname,
      lastname,
      username,
      password,
      nic,
      telno,
      role,
      licenseNumber,
    } = formData;
    if (firstname && lastname && username && password && nic && telno && role) {
      if (role === "FUELSTATION_OWNER") {
        return !!licenseNumber;
      }
      return true;
    }
    return false;
  };

  const handleChange = (e) => {
    setError("");
    const { name, value } = e.target;
    if (name === "role") {
      setLicense(value === "FUELSTATION_OWNER");
    }
    setFormData({ ...formData, [name]: value });
  };
  const handleLicense = () => {
    setLicense(true);
  };

  const handleSendOtp = async () => {
    if (!formData.telno) {
      setError("Please enter your phone number.");
      return;
    }

    try {
      setIsLoading(true);
      const response = await UserAccountApi.sendOtpPhoneNumber(formData.telno);
      if (response.statusCode === 200) {
        setOtpSent(true);
        setError("");
        setSuccess("OTP sent successfully.");
      }
    } catch (error) {
      setError(error.response?.message || error.message);
    } finally {
      setIsLoading(false);
    }
  };

  const handleVerifyOtp = async () => {
    if (!otp) {
      setError("Please enter the OTP.");
      return;
    }

    try {
      setIsLoading(true);
      const response = await UserAccountApi.verifyOtpNumber({
        telno: formData.telno,
        otp,
      });
      if (response.statusCode === 200) {
        setOtpVerified(true);
        setError("");
        setSuccess("OTP verified successfully.");
      }
    } catch (error) {
      setError(error.response?.message || error.message);
    } finally {
      setIsLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateForm()) {
      setError("Please fill in all the required fields.");
      return;
    }

    try {
      const response = await UserAccountApi.registerUser(formData);
      console.log(response);
      if (response.statusCode === 200) {
        setFormData({
          firstname: "",
          lastname: "",
          username: "",
          password: "",
          nic: "",
          telno: "",
          role: "",
          licenseNumber: "",
        });
        setLicense(false);
        setError("");
        setSuccess("User Account Registration successful!");
      }
    } catch (error) {
      setSuccess("");
      setError(error.response?.data?.message || error.message);
    }
  };

  return (
    <div className="bg-slate-800 h-screen w-full fixed">
      <div className="container text-sm mb-32">
        <div className="flex justify-center w-full">
          <motion.div
            variants={SlideLeft(0.2)}
            initial="hidden"
            whileInView="visible"
            className="w-1/2 flex flex-col mt-3"
          >
            {error && <Error error={error} setError={setError} />}
            {success && <Success success={success} setSuccess={setSuccess} />}
            <form onSubmit={handleSubmit} className="register-form">
              <h1 className="text-4xl font-extrabold text-white m-5">
                Sign up
              </h1>
              <div className="m-5">
                <label className="block my-1 text-neutral-400">Tel No</label>
                <input
                  type="text"
                  name="telno"
                  value={formData.telno}
                  onChange={handleChange}
                  placeholder="+94xxxxxxxx"
                  className="bg-gray-200 p-1 w-full"
                  disabled={otpSent}
                />
                {!otpSent ? (
                  <button
                    type="button"
                    className="bg-blue-700 text-white px-6 py-1 mt-3"
                    onClick={handleSendOtp}
                    disabled={isLoading}
                  >
                    {isLoading ? (
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
                      "Send OTP"
                    )}
                  </button>
                ) : (
                  <div className="mt-4">
                    <label className="block my-1 text-neutral-400">
                      Enter OTP
                    </label>
                    <input
                      type="text"
                      value={otp}
                      onChange={(e) => setOtp(e.target.value)}
                      className="bg-gray-200 p-1 w-full"
                    />
                    <button
                      type="button"
                      className="bg-blue-700 text-white px-6 py-1 mt-3"
                      onClick={handleVerifyOtp}
                      disabled={isLoading}
                    >
                      {isLoading ? (
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
                        "Verify OTP"
                      )}
                    </button>
                  </div>
                )}
              </div>
              {otpVerified && (
                <>
                  <div className="grid grid-cols-2 m-5">
                    <div className="mr-3">
                      <label className="block my-1 text-neutral-400">
                        First Name
                      </label>
                      <input
                        type="text"
                        name="firstname"
                        value={formData.firstname}
                        onChange={handleChange}
                        placeholder="firstname"
                        className="bg-gray-200 p-1 rounded-sm text-md w-full"
                      />
                    </div>
                    <div className="form-group">
                      <label className="block my-1 text-neutral-400">
                        Last Name
                      </label>
                      <input
                        type="text"
                        name="lastname"
                        value={formData.lastname}
                        onChange={handleChange}
                        placeholder="lastname"
                        className="bg-gray-200 p-1 w-full"
                      />
                    </div>
                  </div>
                  <div className="grid grid-cols-2 m-5">
                    <div className="mr-3">
                      <label className="block my-1 text-neutral-400">
                        Username
                      </label>
                      <input
                        type="text"
                        name="username"
                        value={formData.username}
                        onChange={handleChange}
                        placeholder="username"
                        className="bg-gray-200 p-1 rounded-sm text-md w-full"
                      />
                    </div>
                    <div className="form-group">
                      <label className="block my-1 text-neutral-400">
                        Password
                      </label>
                      <input
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        placeholder="password"
                        className="bg-gray-200 p-1 w-full"
                      />
                    </div>
                  </div>
                  <div className="m-5">
                    <label className="block my-1 text-neutral-400">NIC</label>
                    <input
                      type="text"
                      name="nic"
                      value={formData.nic}
                      onChange={handleChange}
                      placeholder="NIC number"
                      className="bg-gray-200 p-1 w-full"
                    />
                  </div>
                  <div className="m-5">
                    <label className="block my-1 text-neutral-400">Role</label>
                    <select
                      name="role"
                      value={formData.role}
                      onChange={handleChange}
                      className="bg-gray-200 p-1 w-full"
                    >
                      <option value="">Select Role</option>
                      <option value="FUELSTATION_OWNER">
                        Fuel Station Owner
                      </option>
                      <option value="VEHICLE_OWNER">Vehicle Owner</option>
                    </select>
                  </div>
                  {formData.role === "FUELSTATION_OWNER" && (
                    <div className="m-5">
                      <label className="block my-1 text-neutral-400">
                        License Number
                      </label>
                      <input
                        type="text"
                        name="licenseNumber"
                        value={formData.licenseNumber}
                        onChange={handleChange}
                        placeholder="License number"
                        className="bg-gray-200 p-1 w-full"
                      />
                    </div>
                  )}
                  <div className="m-5">
                    <button
                      type="submit"
                      className="bg-blue-700 text-white px-6 py-1 w-full"
                    >
                      Create an account
                    </button>
                  </div>
                </>
              )}
            </form>
          </motion.div>
        </div>
      </div>
    </div>
  );
};

export default UserRegisterationForm;
