import React, { useState } from "react";
import UserAccountApi from "../apiservice/UserAccountApi";
import { useNavigate } from "react-router-dom";

const ForgotPassword = () => {
  const [step, setStep] = useState(1);
  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState(new Array(6).fill(""));
  const [newPassword, setNewPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleSendOtp = async () => {
    try {
      if (email.trim() === "") {
        setMessage("Please enter a valid email.");
        return;
      }
      const response = await UserAccountApi.sendOtp(email);
      localStorage.setItem("email", email);
      setMessage("OTP sent successfully!");
      setStep(2);
    } catch (error) {
      const errorMessage =
        error.response?.data?.message || error.message || "An error occurred.";
      setMessage(errorMessage);
    }
  };

  const handleChangeOtp = (value, index) => {
    if (!/^\d$/.test(value) && value !== "") return; 
    const updatedOtp = [...otp];
    updatedOtp[index] = value;
    setOtp(updatedOtp);

    
    if (value !== "" && index < 5) {
      document.getElementById(`otp-input-${index + 1}`).focus();
    }
  };

  const handleKeyDown = (e, index) => {
    if (e.key === "Backspace" && otp[index] === "" && index > 0) {
      document.getElementById(`otp-input-${index - 1}`).focus();
    }
  };

  const handleVerifyOtp = async () => {
    try {
      const otpValue = otp.join(""); 
      if (otpValue.length < 6) {
        setMessage("Please enter the complete 6-digit OTP.");
        return;
      }
      const email = localStorage.getItem("email");
      if (!email) {
        setMessage("Email not found. Please try again.");
        return;
      }

      const response = await UserAccountApi.verifyOtp(email, otpValue);
      if (response.statusCode === 200) {
        setMessage("OTP verified successfully!");
        localStorage.setItem("forgetotp", otpValue);
        setStep(3);
      } else {
        setMessage(response.data.message || "Invalid OTP.");
      }
    } catch (error) {
      const errorMessage =
        error.response?.data?.message || error.message || "An error occurred.";
      setMessage(errorMessage);
    }
  };

  const handleResetPassword = async () => {
    try {
      if (newPassword.trim() === "") {
        setMessage("Please enter a new password.");
        return;
      }
      const email = localStorage.getItem("email");
      const forgetotp = localStorage.getItem("forgetotp");

      const response = await UserAccountApi.resetPassword(
        email,
        forgetotp,
        newPassword
      );

      if (response.statusCode === 200) {
        setMessage("Password reset successful!");
        setStep(4);
      } else {
        setMessage(response.data.message || "Password reset failed.");
      }
    } catch (error) {
      const errorMessage =
        error.response?.data?.message || error.message || "An error occurred.";
      setMessage(errorMessage);
    }
  };

  return (
    <>
      <div className="bg-slate-800 h-44"></div>
      <div className="grid grid-cols-2 container items-center">
        <div>
          <img src="..\src\Assets\password.png" alt="" />
        </div>
        <div className="min-h-screen flex items-center justify-center">
          {step === 1 && (
            <div className="p-6 w-full">
              <h2 className="text-5xl font-bold mb-20">Forgot Password?</h2>
              <p className="my-3">Please enter your email below</p>
              <input
                type="email"
                placeholder="Enter your email"
                className="w-full p-2 border-b-2 mb-4 focus:outline-none focus:ring-2 focus:ring-blue-500"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <button
                onClick={handleSendOtp}
                className="w-full bg-blue-800 text-white py-2 rounded hover:bg-blue-600"
              >
                Send OTP
              </button>
              {message && <p className="mt-2 text-red-500 text-sm">{message}</p>}
            </div>
          )}

          {step === 2 && (
            <div className="bg-white p-6 w-full backdrop-blur-sm text-center">
              <h2 className="text-3xl font-bold mb-3 text-center">Enter OTP</h2>
              <p className="my-6 text-center">Enter the 6-digit verification code that was sent to your email</p>
              <div className="flex justify-center gap-3 mb-6 ">
                {otp.map((value, index) => (
                  <input
                    key={index}
                    id={`otp-input-${index}`}
                    type="text"
                    maxLength="1"
                    value={value}
                    onChange={(e) => handleChangeOtp(e.target.value, index)}
                    onKeyDown={(e) => handleKeyDown(e, index)}
                    className="w-16 h-20 text-center text-xl border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 bg-neutral-100"
                  />
                ))}
              </div>
              <button
                onClick={handleVerifyOtp}
                className="w-[200px] bg-blue-800 text-white py-2 rounded hover:bg-blue-600 mt-5"
              >
                Verify OTP
              </button>
              {message && <p className="mt-2 text-red-500 text-sm text-center">{message}</p>}
            </div>
          )}

          {step === 3 && (
            <div className="bg-white p-6 w-full backdrop-blur-sm">
              <h2 className="text-2xl font-bold mb-4">Reset Password</h2>
              <input
                type="password"
                placeholder="Enter new password"
                className="w-full p-2 border-b-2 mb-4 focus:outline-none focus:ring-2 focus:ring-blue-500"
                value={newPassword}
                onChange={(e) => setNewPassword(e.target.value)}
              />
              <button
                onClick={handleResetPassword}
                className="w-full bg-blue-800 text-white py-2 rounded hover:bg-blue-600"
              >
                Reset Password
              </button>
              {message && <p className="mt-2 text-red-500 text-sm">{message}</p>}
            </div>
          )}

          {step === 4 && (
            <div className="p-6 w-full">
              <div>
                <img src="src/Assets/security.png" alt="" />
              </div>
              <h2 className="text-lg font-semibold mb-4 text-center text-neutral-900">
                {message}
              </h2>
              <button
                onClick={() => navigate("/login")}
                className="w-full p-2 bg-blue-800 border-b-2  text-white mb-4 focus:outline-none focus:ring-2 focus:ring-blue-500"
              >
                Go to Login
              </button>
            </div>
          )}
        </div>
      </div>
    </>
  );
};

export default ForgotPassword;
