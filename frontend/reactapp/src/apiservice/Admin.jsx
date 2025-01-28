import axios from "axios";

export default class Authentication {
  static BASE_URL = "http://localhost:8080";

  static getHeader() {
    const token = localStorage.getItem("token");
    return {
      Authorization: `Bearer ${token}`,
      "Content-type": "application/json",
    };
  }

   static async getStations(){
      const response = await axios.get(`${this.BASE_URL}/api/admin/get-stations-with-status`);
      return response.data;
   }
   static async updateStationFuel(stationId,fuel){
     
     const response = await axios.post(`${this.BASE_URL}/api/fuel/addfuel/${stationId}`,fuel);
     return response.data;
   }
   static async getRegisterdStations(){
     const response = await axios.get(`${this.BASE_URL}/api/vehicle/getAllVehicles`);
     return response.data;
   }
}