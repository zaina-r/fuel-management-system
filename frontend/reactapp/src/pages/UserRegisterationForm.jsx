import React, { useState } from "react";
import axios from "axios";

const UserRegisterationForm = () => {
  const [licese,setLicense]=useState(false)
  const [formData, setFormData] = useState({
    firstname: "",
    lastname: "",
    username: "",
    password: "",
    nic: "",
    telno: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    console.log(value)
    if(value=="FUELSTATION_OWNER"){
         setLicense(true)
    }
    if(value=="VEHICLE_OWNER"){
        setLicense(false)
    }
    setFormData({ ...formData, [name]: value });
  };

  const handleLicense=()=>{
       setLicense(true);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/register",
        formData
      );
      console.log(response.data);
      alert("Registration successful!");
    } catch (error) {
      alert("Error during registration: " + error.response.data);
    }
  };

  return (
    <>
      <div className="bg-slate-800 h-44 "></div>
      <div className="container  text-sm ">
        <div className="flex justify-center items-center">
          <div className="w-1/2 bg-[url('')]">
            <h1></h1>
          </div>
          <div className="w-1/2 flex flex-col ">
            <form onSubmit={handleSubmit} className="register-form">
              <h1 className="m-5 text-xl font-semibold">Account Details</h1>
              <div className="grid grid-cols-2 m-5">
                <div className="mr-3">
                  <label className="block my-1">firstname</label>
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
                  <label className="block my-1">lastname</label>
                  <input
                    type="text"
                    name="lastname"
                    value={formData.lastname}
                    onChange={handleChange}
                    placeholder="lastname"
                    className="bg-gray-200 p-1 w-full "
                  />
                </div>
              </div>

              <div className="grid grid-cols-2 m-5">
                <div className="mr-3">
                  <label className="block my-1">NIC</label>
                  <input
                    type="text"
                    name="nic"
                    value={formData.nic}
                    onChange={handleChange}
                    placeholder="nic"
                    className="bg-gray-200 p-1 w-full"
                  />
                </div>
                <div className="form-group">
                  <label className="block my-1">Telphn No</label>
                  <input
                    type="text"
                    name="telno"
                    value={formData.telno}
                    onChange={handleChange}
                    placeholder="+94xxxxxxxx"
                    className="bg-gray-200 p-1 w-full"
                  />
                </div>
              </div>

              <div className="grid grid-cols-2 m-5">
                <div className="mr-3">
                  <label className="block my-1">username</label>
                  <input
                    type="email"
                    name="username"
                    value={formData.username}
                    onChange={handleChange}
                    placeholder="name@gmail.com"
                    className="bg-gray-200 p-1 w-full"
                  />
                </div>
                <div className="form-group">
                  <label className="block my-1">Password</label>
                  <input
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    placeholder="your password"
                    className="bg-gray-200 p-1 w-full"
                  />
                </div>
              </div>
              <div className="m-5 flex flex-col   gap-3">
                <label>Role</label>
                <select
                  name="role"
                  value={formData.role}
                  onChange={handleChange}
                  className="bg-neutral-200 p-2"
                >
                  <option value="VEHICLE_OWNER" >Vehicle Owner</option>
                  <option value="FUELSTATION_OWNER">Station Owner</option>
                </select>
              </div>
            {licese && ( <div className="m-5">
                  <label htmlFor="" className="block my-1">License Number</label>
                  <input type="text" 
                      placeholder="fuel station license number"
                      className="bg-neutral-200 p-2 w-full"

                  />
              </div>)}
              <div className="m-5">
                <input type="checkbox" />
                <label className="ml-2">
                  I agree to the{" "}
                  <a href="" className="text-blue-700">
                    terms and conditions
                  </a>
                </label>
              </div>

              <button
                type="submit"
                className="bg-blue-700 text-white px-6 py-1 ml-5 w-[525px]"
              >
                Create an account
              </button>
              <div className="ml-5 mt-3 text-xs">
                <p className="text-neutral-600">
                  Already have an account?
                  <a href="" className="text-blue-800">
                    Login here
                  </a>{" "}
                </p>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};

export default UserRegisterationForm;
