import axios from "axios";

export default class VehicleApi {
  static BASE_URL = "http://localhost:8080";

  static getHeader() {
    const token = localStorage.getItem("token");
    return {
      Authorization: `Bearer ${token}`,
      "Content-type": "application/json",
    };
  }

  static async registerVehicle(vehicleDetails){
    const response=await axios.post(`${this.BASE_URL}/api/verifyAndAddVehicle`,vehicleDetails);
    return response.data;
  }
}