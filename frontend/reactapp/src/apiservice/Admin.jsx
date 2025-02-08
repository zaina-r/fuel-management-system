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

  static async getStations() {
    const response = await axios.get(
      `${this.BASE_URL}/api/admin/get-stations-with-status`,
      {
        headers: this.getHeader(),
      }
    );
    return response.data;
  }
  static async updateStationFuel(stationId, fuel) {
    const response = await axios.post(
      `${this.BASE_URL}/api/fuel/addfuel/${stationId}`,
      fuel,
      {
        headers: this.getHeader(),
      }
    );
    return response.data;
  }
  static async getRegisterdVehicles() {
    const response = await axios.get(
      `${this.BASE_URL}/api/vehicle/getAllVehicles`,
      {
        headers: this.getHeader(),
      }
    );
    return response.data;
  }
  static async getRegisterdStations() {
    const response = await axios.get(
      `${this.BASE_URL}/api/station/allstations`,
      {
        headers: this.getHeader(),
      }
    );
    return response.data;
  }

  static async updateStationFuel(fuel, id) {
    const response = await axios.post(
      `${this.BASE_URL}/api/admin/update-initial-fuel-allocation/${id}`,
      fuel,
      {
        headers: this.getHeader(),
      }
    );
    return response.data;
  }

  static async updateStations(stationId, station) {
    const response = await axios.put(
      `${this.BASE_URL}/api/station/update/${stationId}`,
      station,
      {
        headers: this.getHeader(),
      }
    );
    return response.data;
  }

  static async getStationReports() {
    const response = await axios.get(
      `${this.BASE_URL}/api/fuelAllocation/alltransitions`,
      {
        headers: this.getHeader(),
      }
    );
    return response.data;
  }

  static async getFuelPriceList() {
    const response = await axios.get(`${this.BASE_URL}/api/allfuelPrices`, {
      headers: this.getHeader(),
    });
    return response.data;
  }

  static async updateFuelPrice(fuelId, fuelPrice) {
    const response = await axios.put(
      `${this.BASE_URL}/api/updatePrice/${fuelId}`,
      fuelPrice,
      {
        headers: this.getHeader(),
      }
    );
    return response.data;
  }

  static async getFuelByName(name) {
    const response = await axios.get(`${this.BASE_URL}/api/findfuel/${name}`, {
      headers: this.getHeader(),
    });
    return response.data;
  }
  static async getRoleAdminMemebers() {
    const response = await axios.get(
      `${this.BASE_URL}/api/by-role`,
      {
        headers: this.getHeader(),
      },

      {
        params: { role: "ADMIN" },
      }
    );
    return response.data;
  }
  static async deleteAdmin(userId) {
    const response = await axios.delete(
      `${this.BASE_URL}/api/admin/delete/${userId}`,
      {
        headers: this.getHeader(),
      }
    );
    return response.data;
  }
}
