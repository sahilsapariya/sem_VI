import React, { useState } from "react";

const Form = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    checkbox: {
      option1: false,
      option2: false,
      option3: false,
    },
    radio: "",
    dropdown: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleCheckboxChange = (e) => {
    const { name, checked } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: checked,
    }));
  };

  const handleRadioChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleDropdownChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    console.log(formData);

    setFormData({
      username: "",
      password: "",
    });
  };

  return (
    <div className="form__container">
      <div className="form__header">
        <h1>Fill the Form</h1>
      </div>
      <form method="post" onSubmit={handleSubmit}>
        <div className="form__row">
          <label htmlFor="username">Username</label>
          <input
            type="text"
            placeholder="Enter Username"
            name="username"
            id="username"
            value={formData.username}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form__row">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            placeholder="Enter Password"
            name="password"
            id="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form__row">
          <div>
            <input
              type="checkbox"
              name="option1"
              id="option1"
              checked={formData.checkbox.option1}
              onChange={handleCheckboxChange}
            />
            <label htmlFor="apple">Apple</label>
          </div>
          <div>
            <input
              type="checkbox"
              name="option2"
              id="option2"
              checked={formData.checkbox.option2}
              onChange={handleCheckboxChange}
            />
            <label htmlFor="option2">Option 2</label>
          </div>
          <div>
            <input
              type="checkbox"
              name="option3"
              id="option3"
              checked={formData.checkbox.option3}
              onChange={handleCheckboxChange}
            />
            <label htmlFor="option3">Option 3</label>
          </div>
        </div>
        <div className="form__row">
          <label>Radio Buttons</label>
          <div>
            <input
              type="radio"
              name="radio"
              id="radio1"
              value="option1"
              checked={formData.radio === "option1"}
              onChange={handleRadioChange}
            />
            <label htmlFor="radio1">Option 1</label>
          </div>
          <div>
            <input
              type="radio"
              name="radio"
              id="radio2"
              value="option2"
              checked={formData.radio === "option2"}
              onChange={handleRadioChange}
            />
            <label htmlFor="radio2">Option 2</label>
          </div>
        </div>
        <div className="form__row">
          <label htmlFor="dropdown">Dropdown</label>
          <select
            id="dropdown"
            name="dropdown"
            value={formData.dropdown}
            onChange={handleDropdownChange}
          >
            <option value="">Select an option</option>
            <option value="option1">Option 1</option>
            <option value="option2">Option 2</option>
            <option value="option3">Option 3</option>
          </select>
        </div>

        <div className="form__button">
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
};

export default Form;
