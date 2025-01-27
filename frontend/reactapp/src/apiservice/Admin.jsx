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
      const response = await axios.get(`${this.BASE_URL}/api/station/allstations`);
      return response.data;
   }
}