import React from 'react';
import './Header_style.css';

function Header() {
  return (
    <>
    <div className="logo">Fuel Management System</div>
    <div className="menu-toggle">
      <div></div>
      <div></div>
      <div></div>
    </div>
    <nav id="navMenu">
      <a href="#dashboard">Dashboard</a>
      <a href="#fuel-stock">Fuel Stock</a>
      <a href="#transactions">Transactions</a>
      <a href="#reports">Reports</a>
      <a href="#settings">Settings</a>
      <a href="#logout">Logout</a>
    </nav>
    </>
  )
}

export default Header