import React, { useState } from "react";
import UserAccountApi from "../apiservice/UserAccountApi";
import { useNavigate } from "react-router-dom";

const ForgotPassword = () => {
  const [step, setStep] = useState(1);
  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
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

  const handleVerifyOtp = async () => {
    try {
      if (otp.trim() === "") {
        setMessage("Please enter the OTP.");
        return;
      }
      const email = localStorage.getItem("email");
      if (!email) {
        setMessage("Email not found. Please try again.");
        return;
      }

      const response = await UserAccountApi.verifyOtp(email, otp);
      if (response.statusCode === 200) {
        setMessage("OTP verified successfully!");
        localStorage.setItem("forgetotp", otp);

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
      console.log(message);
    } catch (error) {
      const errorMessage =
        error.response?.data?.message || error.message || "An error occurred.";
      setMessage(errorMessage);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      {step === 1 && (
        <div className="bg-white p-6 rounded-lg shadow-lg w-80">
          <h2 className="text-lg font-bold mb-4">Forgot Password</h2>
          <input
            type="email"
            placeholder="Enter your email"
            className="w-full p-2 border rounded mb-4 focus:outline-none focus:ring-2 focus:ring-blue-500"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <button
            onClick={handleSendOtp}
            className="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600"
          >
            Send OTP
          </button>
          {message && <p className="mt-2 text-red-500 text-sm">{message}</p>}
        </div>
      )}

      {step === 2 && (
        <div className="bg-white p-6 rounded-lg shadow-lg w-80 backdrop-blur-sm">
          <h2 className="text-lg font-bold mb-4">Enter OTP</h2>
          <input
            type="text"
            placeholder="Enter 6-digit OTP"
            className="w-full p-2 border rounded mb-4 focus:outline-none focus:ring-2 focus:ring-blue-500"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
          />
          <button
            onClick={handleVerifyOtp}
            className="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600"
          >
            Verify OTP
          </button>
          {message && <p className="mt-2 text-red-500 text-sm">{message}</p>}
        </div>
      )}

      {step === 3 && (
        <div className="bg-white p-6 rounded-lg shadow-lg w-80 backdrop-blur-sm">
          <h2 className="text-lg font-bold mb-4">Reset Password</h2>
          <input
            type="password"
            placeholder="Enter new password"
            className="w-full p-2 border rounded mb-4 focus:outline-none focus:ring-2 focus:ring-blue-500"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
          />
          <button
            onClick={handleResetPassword}
            className="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600"
          >
            Reset Password
          </button>
          {message && <p className="mt-2 text-red-500 text-sm">{message}</p>}
        </div>
      )}

      {step === 4 && (
        <div className="bg-white p-6 rounded-lg shadow-lg w-80">
          <h2 className="text-lg font-bold mb-4">{message}</h2>
          <button
            onClick={() => navigate("/login")}
            className="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600"
          >
            Go to Login
          </button>
        </div>
      )}
    </div>
  );
};

export default ForgotPassword;
