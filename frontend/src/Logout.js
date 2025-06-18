import React from 'react';
import { useNavigate } from 'react-router-dom';

function Logout() {
  const navigate = useNavigate();
  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/');
  };
  return (
    <button className="btn btn-secondary ms-2" onClick={handleLogout}>Çıkış Yap</button>
  );
}

export default Logout;