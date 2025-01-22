import { useState } from "react";
import Error from "../responseDisplay/Error";
import Success from "../responseDisplay/Success";
import VehicleApi from "../apiservice/VehicleApi";
import { QRCodeSVG } from "qrcode.react";

const VehicleRegistration = () => {
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [instruction, setInstruction] = useState(true);
  const [qrData, setQrData] = useState();
  // {
  //   license_plate_no: "",
  //   vehicle_type: "",
  //   vehicleRegNo: "",
  //   fuel_type: "",
  //   maximumFuelCapacity: "",
  //   availableFuelCapacity: "",
  //   qrCode:null

  // }

  const [formData, setFormData] = useState({
    license_plate_no: "",
    vehicle_type: "",
    vehicleRegNo: "",
    fuel_type: "",
  });

  const validateForm = () => {
    const { license_plate_no, vehicle_type, vehicleRegNo, fuel_type } =
      formData;
    if (license_plate_no && vehicle_type && vehicleRegNo && fuel_type) {
      return true;
    }
    return false;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) {
      setError("fill the all fields");
      return;
    }
    try {
      const response = await VehicleApi.registerVehicle(formData);
      console.log(response);
      if (response.statusCode === 200) {
        setSuccess("Vehicle registration successful");
        setInstruction(false);
        setQrData(response.vehiclesDto);
        console.log(qrData);
        // console.log(response.vehiclesDto.vehicleRegNo)
        setFormData({
          license_plate_no: "",
          vehicle_type: "",
          vehicleRegNo: "",
          fuel_type: "",
        });
        setError("");
      }
    } catch (error) {
      setError(error.response?.data?.message || "An unexpected error occurred");
    }
  };

  return (
    <>
      <div className="bg-slate-800 h-32 "></div>
      {error && <Error error={error} setError={setError} />}
      {success && <Success success={success} setSuccess={setSuccess} />}
      <div className=" container flex w-full text-sm  mt-16">
        <div className="w-3/4 flex flex-col space-y-8 shadow-2xl p-6 rounded-lg">
          <h1 className="text-4xl font-semibold text-neutral-900">
            Vehicle Registration Form
          </h1>
          <form onSubmit={handleSubmit}>
            <div className="flex gap-5">
              <div>
                <label className="block my-1">Vehicle Number</label>
                <input
                  type="text"
                  name="license_plate_no"
                  value={formData.vehicleNumber}
                  onChange={handleChange}
                  placeholder="Enter vehicle number"
                  className="bg-gray-200 p-1 rounded-sm text-md w-full"
                  required
                />
              </div>
              <div>
                <label className="block my-1">Chassis Number</label>
                <input
                  type="text"
                  name="vehicleRegNo"
                  value={formData.vehicleRegNo}
                  onChange={handleChange}
                  placeholder="Enter chassis number"
                  className="bg-gray-200 p-1 rounded-sm text-md w-full"
                  required
                />
              </div>
            </div>
            <div className="flex gap-16 my-7 ">
              <div>
                <label className="block my-1">Vehicle Type</label>
                <select
                  name="vehicle_type"
                  value={formData.vehicle_type}
                  onChange={handleChange}
                  className="bg-gray-200 p-1 rounded-sm text-md w-full"
                  required
                >
                  <option value="">Select vehicle type</option>
                  <option value="Car">Car</option>
                  <option value="Motorcycle">Motorcycle</option>
                  <option value="Truck">Truck</option>
                  <option value="Van">Van</option>
                </select>
              </div>

              <div>
                <label className="block my-1">Fuel Type</label>
                <select
                  name="fuel_type"
                  value={formData.fuel_type}
                  onChange={handleChange}
                  className="bg-gray-200 p-1 rounded-sm text-md w-full"
                  required
                >
                  <option value="">Select fuel type</option>
                  <option value="Petrol">Petrol</option>
                  <option value="Diesel">Diesel</option>
                </select>
              </div>
            </div>

            <br />
            <button type="submit" className="bg-blue-800 p-2 text-white">
              Register Vehicle
            </button>
          </form>
        </div>
      </div>
      <div className="h-[300PX] w-[600px] bg-gradient-to-r from-slate-800 to-gray-800 ml-96 transform -translate-y-56 translate-x-96 rounded-lg">
        {instruction && (
          <div className="">
            <div className="py-3 px-9">
              <h1 className="text-xl text-white my-1">Vehicle Number</h1>
              <p className="text-xs text-neutral-400 my-2">
                The vehicle number is a unique identifier assigned to your
                vehicle. It is usually displayed on the license plate and is
                required for legal and identification purposes. Please enter the
                complete registration number without spaces or special
                characters
              </p>
              <p className="text-neutral-200 text-xs my-1">
                Example: "ABC1234, DL5C4567"
              </p>
            </div>
            <div className="py-3 px-9">
              <h1 className="text-xl text-white my-1">Chassis Number</h1>
              <p className="text-xs text-neutral-400 my-2">
                {" "}
                The chassis number (also known as the VIN or Vehicle
                Identification Number) is a unique 17-character code that
                identifies your vehicle. It can be found on the chassis or frame
                of your vehicle and may be listed in your vehicle's
                documentation (e.g., registration papers). This number is
                crucial for tracking and verifying the vehicle's history
              </p>
              <p className="text-neutral-200 text-xs my-1">
                Example: "1HGBH41JXMN109186"
              </p>
            </div>
          </div>
        )}
        {qrData && (
          <div className="grid grid-cols-2 ">
            <div className="text-white text-sm px-3 py-4">
              <h1 className="text-xl font-bold  ">Vehcile Details</h1>
              <table className="mt-3">
                <tbody>
                  <tr className="">
                    <td className="">Vehicle Number</td>
                    <td className="text-right pl-20 py-1 text-neutral-400 ">
                      {qrData.license_plate_no}
                    </td>
                  </tr>
                  <tr>
                    <td>Chassis Number</td>
                    <td className="text-right py-1  text-neutral-400">
                      {qrData.vehicleRegNo}
                    </td>
                  </tr>
                  <tr>
                    <td>Vehicle Type</td>
                    <td className="text-right py-1  text-neutral-400">
                      {qrData.vehicle_type}
                    </td>
                  </tr>
                  <tr>
                    <td>Fuel Type</td>
                    <td className="text-right py-1  text-neutral-400">
                      {qrData.fuel_type}
                    </td>
                  </tr>
                  <tr>
                    <td>Available Fuel</td>
                    <td className="text-right py-1  text-neutral-400">
                      {qrData.availableFuelCapacity}
                    </td>
                  </tr>
                  <tr>
                    <td>Maximum Fuel</td>
                    <td className="text-right py-1  text-neutral-400">
                      {qrData.maximumFuelCapacity}
                    </td>
                  </tr>
                  <tr>
                    <td>QR Code</td>
                    <td className="text-right py-1  text-neutral-400">
                      {qrData.qrCode}
                    </td>
                  </tr>
                </tbody>
              </table>
              <div className="text-center my-3">
                <button className="bg-green-600 w-full py-1">Download</button>
              </div>
            </div>
            <div className="  flex flex-col items-center ">
              {/* <img
              src="../src/Assets/car0031-QRCODE.png"
              alt=""
              className="h-[300px] w-[300px]"
            /> */}
              <QRCodeSVG value={qrData.qrCode} size={300} />
              {/* <p className="text-white text-xs">QR_CODE</p> */}
            </div>
          </div>
        )}
      </div>
    </>
  );
};

export default VehicleRegistration;
