import React, { useState } from "react";
import UserAccountApi from "../../../apiservice/UserAccountApi";

const UserRegisterationForm = () => {
  const [licese, setLicense] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

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
    if (firstname || lastname || username || password || nic || telno || role) {
      if (role == "FUELSTATION_OWNER") {
        if (!licenseNumber) {
          return false;
        } else {
          return true;
        }
      } else {
        return true;
      }
    } else {
      return false;
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    console.log(value);
    if (value == "FUELSTATION_OWNER") {
      setLicense(true);
    }
    if (value == "VEHICLE_OWNER") {
      setLicense(false);
    }
    setFormData({ ...formData, [name]: value });
  };

  const handleLicense = () => {
    setLicense(true);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm) {
      setError("please fill the fields");
    }
    try {
      const response = await UserAccountApi.registerUser(formData);
      console.log(response.data);
      if (response.statusCode == 200) {
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
        setSuccess("User Account Registration successfull");
      }
    } catch (error) {
      setError(error.response?.data?.message || error.message);
    }
  };

  return (
    <>
      <div className="bg-slate-800 h-32 "></div>
      <div className="container  text-sm mt-10 ">
        <div className="flex justify-center w-full">
          <div className="relative w-1/2 bg-[url('..\src\Assets\fuel2.jpg')] bg-cover bg-center bg-no-repeat">
            <div className="absolute inset-0 bg-black opacity-80 p-8 text-white">
              <h1 className="text-white text-2xl mb-5">
                Register for FuelPass
              </h1>
              <div className="flex flex-col gap-3">
                <p className="flex items-center gap-2">
                  <img
                    src="../src/Assets/accept.png"
                    alt=""
                    className="w-[15px] h-[15px] inline"
                  />
                  <span>First Name</span>
                </p>
                <p className="flex items-center gap-2">
                  <img
                    src="../src/Assets/accept.png"
                    alt=""
                    className="w-[15px] h-[15px] inline"
                  />
                  <span>Last Name</span>
                </p>
                <p className="flex items-start gap-2">
                  <img
                    src="../src/Assets/accept.png"
                    alt=""
                    className="w-[15px] h-[15px] inline"
                  />
                  <span>NIC</span>

                  <span className="text-xs text-neutral-400 ">
                    Enter your National Identity Card number. This helps us
                    verify your identity and ensure that your registration is
                    valid. This is typically a government-issued numberExample:
                    <span className="text-white">
                      "123456789456V / 200023002913"
                    </span>{" "}
                  </span>
                </p>
                <p className="flex items-start gap-2">
                  <img
                    src="../src/Assets/accept.png"
                    alt=""
                    className="w-[15px] h-[15px] inline"
                  />
                  <span>Tel No</span>

                  <span className="text-xs text-neutral-400 ">
                    Enter your telephone number. Please ensure that it is active
                    and that we can reach you if necessary. Use the format
                    +94xxxxxxxx (Sri Lanka)
                    <span className="text-white">
                      <br />
                      Example:"+94123456789"
                    </span>{" "}
                  </span>
                </p>
                <p className="flex items-start gap-2">
                  <img
                    src="../src/Assets/accept.png"
                    alt=""
                    className="w-[15px] h-[15px] inline"
                  />
                  <span>Username</span>
                  <span className="text-xs text-neutral-400">
                    Choose a unique username. This will be used to identify your
                    account. It should be something you’ll remember and may
                    include letters, numbers, and underscores. <br />
                    <span className="text-white">Example:name@gmail.com</span>
                  </span>
                </p>
                <p className="flex items-start gap-2">
                  <img
                    src="../src/Assets/accept.png"
                    alt=""
                    className="w-[15px] h-[15px] inline"
                  />
                  <span>Password</span>
                  <span className="text-xs text-neutral-400">
                    Create a strong password that you can easily remember but is
                    difficult for others to guess. We recommend using a
                    combination of letters, numbers, and special characters..{" "}
                    <br />
                    <span className="text-white">
                      Example:xxxxxx atlease 4 characters
                    </span>
                  </span>
                </p>
                <p className="flex items-start gap-2">
                  <img
                    src="../src/Assets/accept.png"
                    alt=""
                    className="w-[15px] h-[15px] inline"
                  />
                  <span>Role</span>
                  <span className="text-xs text-neutral-400">
                    When you select fuel station owner you need to provide one
                    more secure license No for you station registration <br />
                    <span className="text-white">
                      Example:xxxxxx secure license code for fuel station
                    </span>
                  </span>
                </p>
              </div>
            </div>
          </div>

          <div className="w-1/2 flex flex-col  mt-3">
            {error && <p>{error}</p>}
            {success && <p>{success}</p>}
            <form onSubmit={handleSubmit} className="register-form">
              <h1 className="text-4xl font-extrabold text-neutral-800 m-5">
                Sign up
              </h1>

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
                  <option value="VEHICLE_OWNER">Vehicle Owner</option>
                  <option value="FUELSTATION_OWNER">Station Owner</option>
                </select>
              </div>
              {licese && (
                <div className="m-5">
                  <label htmlFor="" className="block my-1">
                    License Number
                  </label>
                  <input
                    type="text"
                    name="licenseNumber"
                    value={formData.licenseNumber}
                    placeholder="fuel station license number"
                    className="bg-neutral-200 p-2 w-full"
                  />
                </div>
              )}
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
                  <a href="/login" className="text-blue-800">
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
