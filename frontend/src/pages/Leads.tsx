import React from 'react';
import { useGetLeadsQuery } from '../features/crm/crmApi';

const Leads: React.FC = () => {
  const { data: leads, isLoading, error } = useGetLeadsQuery({});

  if (isLoading) return <div>Loading leads...</div>;
  if (error) return <div>Error loading leads.</div>;

  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-semibold">Leads</h1>
        <button className="bg-indigo-600 text-white px-4 py-2 rounded-md hover:bg-indigo-700">Add Lead</button>
      </div>

      <div className="bg-white shadow overflow-hidden sm:rounded-lg">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Source</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {leads?.length === 0 && (
              <tr><td colSpan={4} className="px-6 py-4 text-center text-gray-500">No leads found.</td></tr>
            )}
            {leads?.map((lead: any) => (
              <tr key={lead.id}>
                <td className="px-6 py-4 whitespace-nowrap">{lead.firstName} {lead.lastName}</td>
                <td className="px-6 py-4 whitespace-nowrap">{lead.email}</td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <span className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                    {lead.status}
                  </span>
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{lead.source}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Leads;
