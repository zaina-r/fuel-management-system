import { useState } from "react";

const StationRegistration = () => {
  const [formData, setFormData] = useState({
    LicenceNo: "",
    Sid: "",
    Dealer: "",
    SAddress: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    console.log(FormData);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (LicenceNo === "" || Sid === "" || Dealer === "" || SAddress === "") {
      alert("Please fill all the fields");
    }
    console.log("Form Data:", formData);
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <h2>Station Registration Form</h2>
        <label htmlFor="LicenceNo">Licence No:</label>
        <input
          type="text"
          name="LicenceNo"
          value={formData.LicenceNo}
          onChange={handleChange}
          placeholder="Enter Licence No"
        ></input>
        <br />
        <label htmlFor="Sid">Station ID:</label>
        <input
          type="text"
          name="Sid"
          value={formData.Sid}
          onChange={handleChange}
          placeholder="10XXXX"
        ></input>
        <br />
        <label htmlFor="Dealer">Dealer:</label>
        <input
          type="text"
          name="Dealer"
          value={formData.Dealer}
          onChange={handleChange}
          placeholder="Enter Dealer's Name"
        ></input>
        <br />
        <label htmlFor="SAddress">Station Address:</label>
        <input
          type="text"
          name="SAddress"
          value={formData.SAddress}
          onChange={handleChange}
          placeholder="Station Address"
        ></input>
        <br />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default StationRegistration;
