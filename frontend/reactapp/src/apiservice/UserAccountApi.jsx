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

  static async loginUser(registration) {
    const response = await axios.post(
      `${this.BASE_URL}/api/login`,
      registration
    );
    return response.data;
  }

  static async verifySigningOtp(otp, userId) {
    const response = await axios.post(
      `${this.BASE_URL}/api/two_factor/otp/${otp}`,
      {},
      {
        params: { userId },
      }
    );
    return response.data;
  }

  static async sendOtp(email) {
    const response = await axios.post(
      `${this.BASE_URL}/api/users/reset-password/send-otp`,
      {},
      {
        params: { email },
      }
    );
    return response.data;
  }

  static async verifyOtp(email, otp) {
    const response = await axios.post(
      `${this.BASE_URL}/api/users/verify-otp/${otp}`,
      {},
      {
        params: { email },
      }
    );
    return response.data;
  }

  static async resetPassword(email, otp, password) {
    console.log(otp);
    const response = await axios.patch(
      `${this.BASE_URL}/api/users/reset-password`,
      {
        otp,
        password,
      },
      {
        params: { email },
      }
    );
    return response.data;
  }
}
