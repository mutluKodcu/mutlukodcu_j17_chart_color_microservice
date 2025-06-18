import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Colors() {
  const [colors, setColors] = useState([]);
  const [name, setName] = useState('');
  const [hexCode, setHexCode] = useState('');
  const [editingId, setEditingId] = useState(null);

  const token = localStorage.getItem('token');

  const headers = {
    Authorization: `Bearer ${token}`
  };

  const fetchColors = async () => {
    const res = await axios.get('/api/colors', { headers });
    setColors(res.data);
  };

  const resetForm = () => {
    setName('');
    setHexCode('');
    setEditingId(null);
  };

  const handleSubmit = async () => {
    if (editingId) {
      await axios.put(`/api/colors/${editingId}`, { name, hexCode }, { headers });
    } else {
      await axios.post('/api/colors', { name, hexCode }, { headers });
    }
    resetForm();
    fetchColors();
  };

  const handleEdit = (color) => {
    setName(color.name);
    setHexCode(color.hexCode);
    setEditingId(color.id);
  };

  const handleDelete = async (id) => {
    await axios.delete(`/api/colors/${id}`, { headers });
    fetchColors();
  };

  useEffect(() => {
    fetchColors();
  }, []);

  return (
    <div className="container mt-5">
      <h2>Renk Yönetimi</h2>
      <div className="mb-3">
        <input className="form-control mb-2" value={name} onChange={e => setName(e.target.value)} placeholder="Renk Adı" />
        <input className="form-control mb-2" value={hexCode} onChange={e => setHexCode(e.target.value)} placeholder="#RRGGBB" />
        <button className="btn btn-success" onClick={handleSubmit}>
          {editingId ? 'Güncelle' : 'Ekle'}
        </button>
      </div>
      <ul className="list-group">
        {colors.map(color => (
          <li key={color.id} className="list-group-item d-flex justify-content-between align-items-center">
            <span>
              {color.name} <span style={{ backgroundColor: color.hexCode, padding: '0 10px', marginLeft: '10px' }}>{color.hexCode}</span>
            </span>
            <span>
              <button className="btn btn-warning btn-sm me-2" onClick={() => handleEdit(color)}>Düzenle</button>
              <button className="btn btn-danger btn-sm" onClick={() => handleDelete(color.id)}>Sil</button>
            </span>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Colors;