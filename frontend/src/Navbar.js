import React from 'react';
import { Link } from 'react-router-dom';
import Logout from './Logout';

function Navbar() {
  const isAuthenticated = !!localStorage.getItem('token');

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/home">MutluKodcu App</Link>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            {isAuthenticated && (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/colors">Renkler</Link>
<li className="nav-item"><Link className="nav-link" to="/grafik">Grafik</Link></li>
                </li>
              </>
            )}
          </ul>
          <div className="d-flex">
            {!isAuthenticated ? (
              <>
                <Link to="/" className="btn btn-outline-success me-2">Giriş</Link>
                <Link to="/register" className="btn btn-outline-primary">Kayıt Ol</Link>
              </>
            ) : (
              <Logout />
            )}
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;