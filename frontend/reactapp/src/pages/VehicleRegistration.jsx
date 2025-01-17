import { useState } from 'react';
import './VehicleRegistration_style.css';

const VehicleRegistrationForm = () => {
  const [formData, setFormData] = useState({
    ownerName: '',
    ownerID: '',
    vehicleNumber: '',
    vehicleType: '',
    chassisNumber: '',
    fuelType: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Form Data Submitted:', formData);
    alert('Vehicle registered successfully!');
  };

  return (
    <>
    <div style={{ margin: '20px' }}>
      <h2>Vehicle Registration Form</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Vehicle Owner Name:</label>
          <input
            type="text"
            name="ownerName"
            value={formData.ownerName}
            onChange={handleChange}
            placeholder="Enter owner name"
            required
          />
        </div>
        <div>
          <label>Vehicle Owner ID Number:</label>
          <input
            type="text"
            name="ownerID"
            value={formData.ownerID}
            onChange={handleChange}
            placeholder="Enter owner Id"
            required
          />
        </div>
        <div>
          <label>Vehicle Number:</label>
          <input
            type="text"
            name="vehicleNumber"
            value={formData.vehicleNumber}
            onChange={handleChange}
            placeholder="Enter vehicle number"
            required
          />
        </div>
        <div>
          <label>Vehicle Type:</label>
          <select
            name="vehicleType"
            value={formData.vehicleType}
            onChange={handleChange}
            required
          >
            <option value="">Select vehicle type</option>
            <option value="Car">Car</option>
            <option value="Motorcycle">Motorcycle</option>
            <option value="Truck">Truck</option>
            <option value="Bus">Bus</option>
          </select>
        </div>
        <div>
          <label>Chassis Number:</label>
          <input
            type="text"
            name="chassisNumber"
            value={formData.chassisNumber}
            onChange={handleChange}
            placeholder="Enter chassis number"
            required
          />
        </div>
        <div>
          <label>Fuel Type:</label>
          <select
            name="fuelType"
            value={formData.fuelType}
            onChange={handleChange}
            required
          >
            <option value="">Select fuel type</option>
            <option value="Petrol">Petrol</option>
            <option value="Diesel">Diesel</option>
            <option value="Electric">Electric</option>
            <option value="Hybrid">Hybrid</option>
          </select>
        </div>
        <br />
        <button type="submit">Register Vehicle</button>
      </form>
    </div>
    </>
  );
};

export default VehicleRegistrationForm;
