import React, { useState } from "react";

export const ForgotPassword = () => {
    const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSendOTP = async () => {
    if (!email) {
      setMessage("Please enter a valid email address.");
      return;
    }

    setLoading(true);
    setMessage("");

    try {
      
      await new Promise((resolve) => setTimeout(resolve, 2000)); 
      setMessage("OTP sent to your email address!");
    } catch (error) {
      setMessage("Failed to send OTP. Please try again.");
    } finally {
      setLoading(false);
    }
  };
  return (
    <div className="container">
      <h2>Forgot Password</h2>
      <div className="form">
        <label htmlFor="email">Email Address:</label>
        <input className="input"
          type="email"
          id="email"
          placeholder="Enter your email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          
        />
        <button className="button"
          onClick={handleSendOTP}
          disabled={loading}
        >
          {loading ? "Sending..." : "Send OTP"}
        </button>
        {message && <p className="message">{message}</p>}
      </div>
    </div>
  )
}
