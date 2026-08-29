import React from 'react';
import { useGetContactsQuery } from '../features/crm/crmApi';

const Contacts: React.FC = () => {
  const { data: contacts, isLoading, error } = useGetContactsQuery({});

  if (isLoading) return <div>Loading contacts...</div>;
  if (error) return <div>Error loading contacts.</div>;

  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-semibold">Contacts</h1>
        <button className="bg-indigo-600 text-white px-4 py-2 rounded-md hover:bg-indigo-700">Add Contact</button>
      </div>

      <div className="bg-white shadow overflow-hidden sm:rounded-lg">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Phone</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Job Title</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {contacts?.length === 0 && (
              <tr><td colSpan={4} className="px-6 py-4 text-center text-gray-500">No contacts found.</td></tr>
            )}
            {contacts?.map((contact: any) => (
              <tr key={contact.id}>
                <td className="px-6 py-4 whitespace-nowrap">{contact.firstName} {contact.lastName}</td>
                <td className="px-6 py-4 whitespace-nowrap">{contact.email}</td>
                <td className="px-6 py-4 whitespace-nowrap">{contact.phone}</td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{contact.jobTitle}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Contacts;
