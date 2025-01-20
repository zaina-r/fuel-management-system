import React,{useState} from 'react';

const Contact = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    message: "",
  });
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({...formData,[name]:value}) ;
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form submitted:",formData);
    alert("Yuer message has been sent!");
    setFormData({name:"",email:"",message:""});
  };
  return (
    <div className="contact-page">
      <h1>Contact Us</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Name:</label>
          <input
          type="text"
          id="name"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email:</label>
          <input
          type="email"
          id="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          required
          /> 
        </div>
        <div className="form-group">
          <label htmlFor="message">Message:</label>
          <textarea
          id="message"
          name="message"
          value={formData.message}
          onChange={handleChange}
          required
          />
          </div>
          <button type="submit">Send Message</button>
      </form>
    </div>
  );
}; 
 

export default Contact