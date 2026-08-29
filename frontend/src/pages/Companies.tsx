import React from 'react';
import { useGetCompaniesQuery } from '../features/crm/crmApi';

const Companies: React.FC = () => {
  const { data: companies, isLoading, error } = useGetCompaniesQuery({});

  if (isLoading) return <div>Loading companies...</div>;
  if (error) return <div>Error loading companies.</div>;

  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-semibold">Companies</h1>
        <button className="bg-indigo-600 text-white px-4 py-2 rounded-md hover:bg-indigo-700">Add Company</button>
      </div>

      <div className="bg-white shadow overflow-hidden sm:rounded-lg">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Industry</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Website</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {companies?.length === 0 && (
              <tr><td colSpan={3} className="px-6 py-4 text-center text-gray-500">No companies found.</td></tr>
            )}
            {companies?.map((company: any) => (
              <tr key={company.id}>
                <td className="px-6 py-4 whitespace-nowrap font-medium text-gray-900">{company.name}</td>
                <td className="px-6 py-4 whitespace-nowrap text-gray-500">{company.industry}</td>
                <td className="px-6 py-4 whitespace-nowrap text-indigo-600 hover:text-indigo-900">
                  <a href={company.website} target="_blank" rel="noreferrer">{company.website}</a>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Companies;
