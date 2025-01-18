import React, { useState } from 'react'
import axios from "axios";

const UserRegisterationForm = () => {
    const [formData, setFormData] = useState({
      firstname: "",
      lastname: "",
      username: "",
      password: "",
      nic: "",
      telno: ""
      
      
    });
            
    const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
      e.preventDefault();
      try {
      const response=  await axios.post("http://localhost:8080/api/register", formData);
      console.log(response.data)
            alert("Registration successful!");
      } catch (error) {
        alert("Error during registration: " + error.response.data);
      }
    };

    return (
      <form onSubmit={handleSubmit} className="register-form">
        <h1>Register</h1>
        <div className="form-group">
          <label>firstname:</label>
          <input
            type="text"
            name="firstname"
            value={formData.firstname}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>lastname:</label>
          <input
            type="text"
            name="lastname"
            value={formData.lastname}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>username:</label>
          <input
            type="email"
            name="username"
            value={formData.username}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>NIC:</label>
          <input
            type="nic"
            name="nic"
            value={formData.nic}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>Telphn No:</label>
          <input
            type="text"
            name="telno"
            value={formData.telno}
            onChange={handleChange}
          />
        </div>
        {/* <div className="form-group">
          <label>Role:</label>
          <select
            name="role"
            value={formData.role}
            onChange={handleChange}
          >
            <option value="VEHICLE_OWNER">Vehicle Owner</option>
            <option value="station-owner">Station Owner</option>
          </select>
        </div> */}
        <div className="form-group">
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
          />
        </div>
        <button type="submit" className="register-button">Register</button>
      </form>
    );
  };

export default UserRegisterationForm
