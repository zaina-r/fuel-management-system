import React, { useEffect, useState } from "react";
import UserAccountApi from "../apiservice/UserAccountApi";
import Error from "../responseDisplay/Error";
import Success from "../responseDisplay/Success";

const ProfilePage = () => {
  const [user, setUser] = useState({});
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({});
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  useEffect(() => {
    getUserDetails();
  }, []);

  const getUserDetails = async () => {
    try {
      const response = await UserAccountApi.getUserDetails();
      setUser(response.userAccountDto);
      setFormData(response.userAccountDto);
    } catch (err) {
      console.error(err.message || "An unexpected error occurred.");
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleUpdateDetails = async (formData) => {
    try {
      const response = await UserAccountApi.updateUserDetails(formData);
      if (response.statusCode === 200) {
        setFormData(response.userAccountDto);

        setError("");
        setSuccess("User Account Updated successful!");
      }

      setUser(response.userAccountDto);
      // Initialize formData with user data
    } catch (err) {
      console.error(err.message);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    handleUpdateDetails(formData);

    setIsEditing(false);
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-800 text-white py-12 px-4 sm:px-6 lg:px-8">
      <div className=" w-full  rounded-lg shadow-lg p-6 ">
        {error && <Error error={error} setError={setError} />}
        {success && <Success success={success} setSuccess={setSuccess} />}
        <h2 className="text-2xl font-bold text-center mb-6">Profile Details</h2>

        {isEditing ? (
          <form onSubmit={handleSubmit}>
            <div className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-200">
                  First Name
                </label>
                <input
                  type="text"
                  name="firstname"
                  value={formData.firstname || ""}
                  onChange={handleInputChange}
                  className="mt-1 block w-full px-3 py-2 border rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 text-black"
                  required
                />
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-200">
                  Last Name
                </label>
                <input
                  type="text"
                  name="lastname"
                  value={formData.lastname || ""}
                  onChange={handleInputChange}
                  className="mt-1 block w-full px-3 py-2 border rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 text-black"
                  required
                />
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-200">
                  Mobile Number
                </label>
                <input
                  type="text"
                  name="telno"
                  value={formData.telno || ""}
                  onChange={handleInputChange}
                  className="mt-1 block w-full px-3 py-2 border rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 text-black"
                  required
                />
              </div>

              <div className="mt-6 flex justify-end space-x-4">
                <button
                  type="button"
                  onClick={() => setIsEditing(false)}
                  className="px-4 py-2 text-sm font-medium bg-gray-500 rounded-md hover:bg-gray-600"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 text-sm font-medium bg-blue-600 rounded-md hover:bg-blue-700"
                >
                  Save Changes
                </button>
              </div>
            </div>
          </form>
        ) : (
          <>
            <div className=" flex flex-col items-center">
              <div className="flex items-center justify-center gap-3">
                <div className="w-[300px] h-[1px] bg-neutral-400"></div>
                <div className="w-[100px] h-[100px] border-2 border-neutral-400 rounded-full"></div>
                <div className="w-[300px] h-[1px] bg-neutral-400"></div>
              </div>
              <div className="my-10">
                <div className="flex justify-between items-center  my-6 ">
                  <div>
                    <label className="block text-sm font-medium text-orange-500">
                      First Name
                    </label>
                    <p className="mt-1 text-lg">{user.firstname}</p>
                  </div>

                  <div>
                    <label className="block text-sm font-medium text-orange-500">
                      Last Name
                    </label>
                    <p className="mt-1 text-lg">{user.lastname}</p>
                  </div>
                </div>

                <div className="flex justify-between items-center gap-96  ">
                  <div>
                    <label className="block text-sm font-medium text-orange-500">
                      Mobile Number
                    </label>
                    <p className="mt-1 text-lg">{user.telno}</p>
                  </div>

                  <div>
                    <label className="block text-sm font-medium text-right text-orange-500">
                      Email
                    </label>
                    <p className="mt-1 text-lg">{user.username}</p>
                  </div>
                </div>

                {user.role === "stationOwner" && (
                  <>
                    <div>
                      <label className="block text-sm font-medium text-orange-500">
                        License Number
                      </label>
                      <p className="mt-1 text-sm">{user.licenseNumber}</p>
                    </div>
                  </>
                )}

                <div className="mt-6">
                  <button
                    onClick={() => setIsEditing(true)}
                    className="w-full px-4 py-2 text-sm font-medium bg-blue-600 rounded-md hover:bg-blue-700"
                  >
                    Edit Profile
                  </button>
                </div>
              </div>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default ProfilePage;
