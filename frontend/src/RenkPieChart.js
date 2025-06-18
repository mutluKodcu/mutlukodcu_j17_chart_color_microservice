import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { PieChart, Pie, Cell, Tooltip, Legend, ResponsiveContainer } from 'recharts';

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#AA336A', '#8A2BE2', '#DAA520'];

const RenkPieChart = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem('token');
    axios.get('/api/colors/stat', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }).then(res => {
      const formatted = res.data.map((item) => ({
        name: item.color,
        value: item.count
      }));
      setData(formatted);
    });
  }, []);

  return (
    <div className="container mt-4">
      <h3>Renk Kullanım Grafiği</h3>
      <ResponsiveContainer width="100%" height={300}>
        <PieChart>
          <Pie
            data={data}
            cx="50%"
            cy="50%"
            outerRadius={100}
            fill="#8884d8"
            dataKey="value"
            label
          >
            {data.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
          <Legend />
        </PieChart>
      </ResponsiveContainer>
    </div>
  );
};

export default RenkPieChart;