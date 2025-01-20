import axios from "axios";

export default class UserAccountApi {
  static BASE_URL = "http://localhost:8080";

  static getHeader() {
    const token = localStorage.getItem("token");
    return {
      Authorization: `Bearer ${token}`,
      "Content-type": "application/json",
    };
  }

  //This is a ner user registration
  static async registerUser(registration) {
    const response = await axios.post(
      `${this.BASE_URL}/api/register`,
      registration
    );
    return response.data;
  }
}
