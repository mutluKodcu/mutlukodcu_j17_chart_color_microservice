import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  
  const handleRegister = async () => {
    if (!username || username.length < 3) {
      alert("Kullanıcı adı en az 3 karakter olmalı.");
      return;
    }
    if (!password || password.length < 6) {
      alert("Şifre en az 6 karakter olmalı.");
      return;
    }

    try {
      await axios.post('/api/auth/register', { username, password });
      alert('Kayıt başarılı, giriş yapabilirsiniz.');
      navigate('/');
    } catch (e) {
      alert('Kayıt başarısız!');
    }
  };

  return (
    <div className="container mt-5">
      <h2>Kayıt Ol</h2>
      <input className="form-control mb-2" placeholder="Kullanıcı Adı" value={username} onChange={(e) => setUsername(e.target.value)} />
      <input className="form-control mb-2" placeholder="Şifre" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button className="btn btn-primary" onClick={handleRegister}>Kayıt Ol</button>
    </div>
  );
}

export default Register;