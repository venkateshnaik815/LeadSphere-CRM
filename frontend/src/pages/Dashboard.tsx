import React from 'react';

const Dashboard: React.FC = () => {
  return (
    <div>
      <h1 className="text-2xl font-semibold mb-4">Dashboard Overview</h1>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
          <h3 className="text-gray-500 text-sm font-medium">Total Leads</h3>
          <p className="text-3xl font-bold mt-2">--</p>
        </div>
        <div className="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
          <h3 className="text-gray-500 text-sm font-medium">Total Contacts</h3>
          <p className="text-3xl font-bold mt-2">--</p>
        </div>
        <div className="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
          <h3 className="text-gray-500 text-sm font-medium">Total Companies</h3>
          <p className="text-3xl font-bold mt-2">--</p>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
