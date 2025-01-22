import React, { useState } from "react";
import user_icon from "../Assets/person.png";
import email_icon from "../Assets/email.png";
import password_icon from "../Assets/password.png";
import { NavLink, useNavigate } from "react-router-dom";

function UserLogin() {
  const [action, setAction] = useState("Login");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [otpBar, setOtpBar] = useState(false);
  const navigate = useNavigate();

  const validate = async () => {
    console.log(password);
    console.log(username);
    if (username && password) {
      return true;
    }
    return false;
  };

  const handleLogin = async () => {
    if (!validate()) {
      setError("fill the input fields");
    }

    try {
      const response = await UserAccountApi.loginUser({ username, password });
      console.log("The response is " + response);
      if (response.statusCode === 200) {
        const id = response.userAccountDto.userId;
        localStorage.setItem("userId", id);
        const role = response.userAccountDto.role;
        if (role == "VEHICLE_OWNER") {
          localStorage.setItem("token", response.token);
          localStorage.setItem("role", response.role);
          setSuccessMessage("User has successfully login");
          navigate("/");
        }
        if (role == "FUELSTATION_OWNER") {
          localStorage.setItem("token", response.token);
          localStorage.setItem("role", response.role);
          setOtpBar(true);
        }
      }
    } catch (error) {
      setError(error.response?.data?.message || error.message);
    }
  };

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };
  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const [otp, setOtp] = useState(["", "", "", "", "", ""]);

  const handleOtpChange = (e, index) => {
    const value = e.target.value;
    const updatedOtp = [...otp];
    updatedOtp[index] = value;
    setOtp(updatedOtp);

    if (value && index < otp.length - 1) {
      document.getElementById(`otp-input-${index + 1}`).focus();
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const otpValue = otp.join("");

    if (otpValue.length < 6) {
      setError("Please enter a valid 6-digit OTP.");
      return;
    }

    setError("");

    try {
      const result = await UserAccountApi.verifyOtp(otpValue);
      console.log(result);
      if (result.statusCode == 200) {
        console.log("OTP verified successfully!");
        navigate("/");
      } else {
        setError("OTP verification failed. Please try again.");
      }
    } catch (error) {
      console.error("Error verifying OTP:", error);
      setError("There was an error verifying the OTP. Please try again.");
    } finally {
    }
  };

  
  return (
    <>
      <div>
        <div className="bg-slate-800 h-44 "></div>
        {error && <p>{error}</p>}
        {successMessage && <p>{successMessage}</p>}
        <div className="container text-sm mt-24">
          <div className="flex items-center w-full">
            <div className="w-1/2">
              {otpBar && (
                <div className="m-5 flex justify-center items-center">
                  <form
                    onSubmit={handleSubmit}
                    className="flex flex-col items-center space-y-4"
                  >
                    {/* OTP Input Fields */}
                    <div className="flex space-x-2">
                      {otp.map((_, index) => (
                        <input
                          key={index}
                          id={`otp-input-${index}`}
                          type="text"
                          maxLength="1"
                          value={otp[index]}
                          onChange={(e) => handleOtpChange(e, index)}
                          onFocus={(e) => e.target.select()}
                          className="w-12 h-12 text-center text-2xl border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                      ))}
                    </div>

                    {/* Submit Button */}
                    <button
                      type="submit"
                      className="mt-4 px-6 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
                    >
                      Submit
                    </button>
                  </form>
                </div>
              )}
            </div>
            <div className="w-1/2 flex flex-col gap-5">
              <div>
                <h1 className="text-4xl font-extrabold text-neutral-800">
                  Sign in
                </h1>
                <p className="my-3">
                  Don't have an account{" "}
                  <span className="text-blue-700 hover:underline hover:cursor-pointer">
                    <a href="/register">Register here</a>
                  </span>
                </p>
              </div>

              <div>
                <div className="my-1">
                  <label htmlFor="email">Username</label>
                </div>
                <div className="relative">
                  <input
                    type="text"
                    placeholder="name@gmail.com"
                    onChange={handleUsernameChange}
                    className="bg-gray-200 p-1 rounded-sm text-md w-full"
                  />
                  <div className="absolute right-2 top-2 ">
                    <img
                      src="..\src\Assets\email.png"
                      alt=""
                      className="w-3 h-3"
                    />
                  </div>
                </div>
              </div>
              <div>
                <div className="my-1">
                  <label htmlFor="password">Password</label>
                </div>
                <div className="relative">
                  <input
                    type="text"
                    placeholder="your password"
                    onChange={handlePasswordChange}
                    className="bg-gray-200 p-1 rounded-sm text-md w-full"
                  />
                  <div className="absolute right-2 top-2 ">
                    <img
                      src="..\src\Assets\password.png"
                      alt=""
                      className="w-3 h-3"
                    />
                  </div>
                </div>
              </div>
              <div className="flex justify-between items-center">
                <div>
                  <input type="checkbox" />
                  <label htmlFor="remember">Remember me</label>
                </div>
                <div className="text-blue-900 hover:underline hover:cursor-pointer">
                  <a href="/forgotpassword">Forgot Password?</a>
                </div>
              </div>
              <div className="w-full">
                <button
                  className="bg-blue-800 w-full text-white p-2"
                  onClick={handleLogin}
                >
                  Sign in
                </button>
              </div>
              <div className="flex items-center">
                <div className="w-1/2 h-[1px] bg-gray-300"></div>
                <p className="mx-5 text-neutral-500">or</p>
                <div className="w-1/2 h-[1px] bg-gray-300"></div>
              </div>
              <div className="flex justify-center items-center space-x-5">
                <img
                  src="..\src\Assets\google-icon-251x256-2pod32cq.png"
                  alt=""
                  className="w-7 h-7"
                />
                <img
                  src="..\src\Assets\facebook-color-icon-512x512-y7c9r37n.png"
                  alt=""
                  className="w-7 h-7"
                />
                <img
                  src="..\src\Assets\apple-icon-430x512-tmf55ggw.png"
                  alt=""
                  className="w-8 h-8 mb-2"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default UserLogin;
