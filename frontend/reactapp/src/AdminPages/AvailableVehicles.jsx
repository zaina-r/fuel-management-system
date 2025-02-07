import React, { useEffect, useState } from "react";
import { Pencil, Trash2 } from "lucide-react";
import VehicleApi from "../apiservice/VehicleApi";

const AvailableVehicles = () => {
  const [vehicleList, setVehicleList] = useState([]);
  const [editingVehicle, setEditingVehicle] = useState(null);
  const [addingVehicle, setAddingVehicle] = useState(false);
  const [formData, setFormData] = useState({
    vehicleType: "",
    fuelCapacity: "",
  });

  const handleEdit = (vehicle) => {
    setEditingVehicle(vehicle);
    setFormData({
      vehicleType: vehicle.vehicleType,
      fuelCapacity: vehicle.fuelCapacity,
    });
  };

  const handleDelete = async (id) => {
    const response = await VehicleApi.deleteAvailableVehicle(id);
    if (response.statusCode === 200) {
      console.log("Vehicle deleted successfully");
      setVehicleList(vehicleList.filter((vehicle) => vehicle.id !== id));
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleUpdate = async () => {
    const updatedVehicle = {
      ...editingVehicle,
      vehicleType: formData.vehicleType,
      fuelCapacity: formData.fuelCapacity,
    };

    const response = await VehicleApi.updateAvailableVehicle(
      editingVehicle.id,
      updatedVehicle
    );
    if (response.statusCode === 200) {
      setVehicleList(
        vehicleList.map((vehicle) =>
          vehicle.id === editingVehicle.id ? updatedVehicle : vehicle
        )
      );
      setEditingVehicle(null);
      console.log("Vehicle updated successfully");
    }
  };

  const handleAdd = async () => {
    const newVehicle = {
      vehicleType: formData.vehicleType,
      fuelCapacity: formData.fuelCapacity,
    };

    const response = await VehicleApi.addAvailableVehicle(newVehicle);
    if (response.statusCode === 200) {
      setVehicleList([...vehicleList, response.vehicle]);
      setAddingVehicle(false);
      setFormData({ vehicleType: "", fuelCapacity: "" });
      console.log("Vehicle added successfully");
    }
  };

  useEffect(() => {
    const getVehicleDetails = async () => {
      const response = await VehicleApi.getAvailableVehicles();
      if (response.statusCode === 200) {
        setVehicleList(response.vehicleDtoList);
      }
    };
    getVehicleDetails();
  }, []);

  return (
    <div className="p-4 relative">
      <div className={`${editingVehicle || addingVehicle ? "blur-sm" : ""}`}>
        <h1 className="text-2xl font-bold mb-4 text-white">Available Vehicles</h1>
        <button
          className="bg-green-500 text-white px-4 py-2 rounded-lg mb-4 hover:bg-green-600"
          onClick={() => setAddingVehicle(true)}
        >
          Add Vehicle
        </button>

        <table className="min-w-full text-neutral-400 border-separate border-spacing-y-2">
          <thead>
            <tr className="text-left">
              <th className="py-2 text-orange-500">ID</th>
              <th className="py-2 text-orange-500">Vehicle Type</th>
              <th className="py-2 text-orange-500">Max Fuel Capacity</th>
              <th className="py-2 text-orange-500">Actions</th>
            </tr>
          </thead>
          <tbody>
            {vehicleList && vehicleList.length > 0 ? (
              vehicleList.map((vehicle) =>
                vehicle && vehicle.id ? (
                  <tr key={vehicle.id} className="">
                    <td className="py-2 px-4">{vehicle.id}</td>
                    <td className="py-2 px-4">{vehicle.vehicleType}</td>
                    <td className="py-2 px-4">{vehicle.fuelCapacity}L</td>
                    <td className="py-2 px-4 flex space-x-4">
                      <button
                        className="bg-blue-500 text-white p-2 rounded-lg hover:bg-blue-600"
                        onClick={() => handleEdit(vehicle)}
                      >
                        <Pencil size={18} />
                      </button>
                      <button
                        className="bg-red-600 text-white p-2 rounded-lg hover:bg-red-600"
                        onClick={() => handleDelete(vehicle.id)}
                      >
                        <Trash2 size={18} />
                      </button>
                    </td>
                  </tr>
                ) : null
              )
            ) : (
              <tr>
                <td colSpan="4" className="text-center py-4 text-white">
                  No vehicles available.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {(editingVehicle || addingVehicle) && (
        <div className="fixed inset-0 bg-black bg-opacity-50 backdrop-blur-sm flex items-center justify-center z-50">
          <div className="bg-gray-800 p-6 rounded-xl shadow-2xl w-full max-w-md text-white">
            <h2 className="text-xl font-bold mb-4 text-center">
              {editingVehicle
                ? `Edit Vehicle ID: ${editingVehicle.id}`
                : "Add New Vehicle"}
            </h2>
            <div className="mb-4">
              <label className="block text-sm font-medium mb-1">Vehicle Type:</label>
              <input
                type="text"
                name="vehicleType"
                value={formData.vehicleType}
                onChange={handleInputChange}
                className="w-full p-2 rounded-lg bg-gray-700 text-white"
              />
            </div>
            <div className="mb-4">
              <label className="block text-sm font-medium mb-1">Max Fuel Capacity (L):</label>
              <input
                type="number"
                name="fuelCapacity"
                value={formData.fuelCapacity}
                onChange={handleInputChange}
                className="w-full p-2 rounded-lg bg-gray-700 text-white"
              />
            </div>
            <div className="flex justify-between mt-6">
              <button
                className="bg-green-500 px-4 py-2 rounded-lg hover:bg-green-600"
                onClick={editingVehicle ? handleUpdate : handleAdd}
              >
                {editingVehicle ? "Save Changes" : "Add Vehicle"}
              </button>
              <button
                className="bg-gray-500 px-4 py-2 rounded-lg hover:bg-gray-600"
                onClick={() => {
                  setEditingVehicle(null);
                  setAddingVehicle(false);
                  setFormData({ vehicleType: "", fuelCapacity: "" });
                }}
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default AvailableVehicles;
