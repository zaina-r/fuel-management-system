import React, { useState } from "react";
import user_icon from "../Assets/person.png";
import email_icon from "../Assets/email.png";
import password_icon from "../Assets/password.png";
import { NavLink, useNavigate } from "react-router-dom";

function UserLogin() {
  const [action, setAction] = useState("Login");

  const handleLogin = () => {};
  return (
    <>
      <div>
        <div className="bg-slate-800 h-44 "></div>
        <div className="container text-sm mt-24">
          <div className="flex items-center w-full">
            <div className="w-1/2">ejlnlkefnkle</div>
            <div className="w-1/2 flex flex-col gap-5">
              <div>
                <h1 className="text-4xl font-extrabold text-neutral-800">
                  Sign in
                </h1>
                <p className="my-3">
                  Don't have an account{" "}
                  <span className="text-blue-700 hover:underline hover:cursor-pointer">
                    <a href="/register">Register here</a>
                  </span>
                </p>
              </div>

              <div>
                <div className="my-1">
                  <label htmlFor="email">Username</label>
                </div>
                <div className="relative">
                  <input
                    type="text"
                    placeholder="name@gmail.com"
                    className="bg-gray-200 p-1 rounded-sm text-md w-full"
                  />
                  <div className="absolute right-2 top-2 ">
                    <img
                      src="..\src\Assets\email.png"
                      alt=""
                      className="w-3 h-3"
                    />
                  </div>
                </div>
              </div>
              <div>
                <div className="my-1">
                  <label htmlFor="password">Password</label>
                </div>
                <div className="relative">
                  <input
                    type="text"
                    placeholder="your password"
                    className="bg-gray-200 p-1 rounded-sm text-md w-full"
                  />
                  <div className="absolute right-2 top-2 ">
                    <img
                      src="..\src\Assets\password.png"
                      alt=""
                      className="w-3 h-3"
                    />
                  </div>
                </div>
              </div>
              <div className="flex justify-between items-center">
                <div>
                  <input type="checkbox" />
                  <label htmlFor="remember">Remember me</label>
                </div>
                <div className="text-blue-900 hover:underline hover:cursor-pointer">
                  <a href="/forgotpassword">Forgot Password?</a>
                </div>
              </div>
              <div className="w-full">
                <button
                  className="bg-blue-800 w-full text-white p-2"
                  onClick={handleLogin}
                >
                  Sign in
                </button>
              </div>
              <div className="flex items-center">
                <div className="w-1/2 h-[1px] bg-gray-300"></div>
                <p className="mx-5 text-neutral-500">or</p>
                <div className="w-1/2 h-[1px] bg-gray-300"></div>
              </div>
              <div className="flex justify-center items-center space-x-5">
                <img
                  src="..\src\Assets\google-icon-251x256-2pod32cq.png"
                  alt=""
                  className="w-7 h-7"
                />
                <img
                  src="..\src\Assets\facebook-color-icon-512x512-y7c9r37n.png"
                  alt=""
                  className="w-7 h-7"
                />
                <img
                  src="..\src\Assets\apple-icon-430x512-tmf55ggw.png"
                  alt=""
                  className="w-8 h-8 mb-2"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default UserLogin;
