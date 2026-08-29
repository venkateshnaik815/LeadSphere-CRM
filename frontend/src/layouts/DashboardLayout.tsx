import React from 'react';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { logout } from '../features/auth/authSlice';

const DashboardLayout: React.FC = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogout = () => {
    dispatch(logout());
    navigate('/login');
  };

  return (
    <div className="min-h-screen flex bg-gray-100">
      {/* Sidebar */}
      <div className="w-64 bg-gray-900 text-white flex flex-col">
        <div className="h-16 flex items-center justify-center border-b border-gray-800">
          <h1 className="text-xl font-bold">LeadSphere CRM</h1>
        </div>
        <nav className="flex-1 px-4 py-6 space-y-2">
          <Link to="/dashboard" className="block px-4 py-2 rounded hover:bg-gray-800">Dashboard</Link>
          <Link to="/leads" className="block px-4 py-2 rounded hover:bg-gray-800">Leads</Link>
          <Link to="/contacts" className="block px-4 py-2 rounded hover:bg-gray-800">Contacts</Link>
          <Link to="/companies" className="block px-4 py-2 rounded hover:bg-gray-800">Companies</Link>
        </nav>
        <div className="p-4 border-t border-gray-800">
          <button 
            onClick={handleLogout}
            className="w-full text-left px-4 py-2 rounded hover:bg-gray-800 text-red-400"
          >
            Logout
          </button>
        </div>
      </div>

      {/* Main Content */}
      <div className="flex-1 flex flex-col">
        <header className="h-16 bg-white shadow-sm flex items-center px-8">
          <h2 className="text-xl font-semibold text-gray-800">CRM Module</h2>
        </header>
        <main className="flex-1 p-8 overflow-y-auto">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default DashboardLayout;
