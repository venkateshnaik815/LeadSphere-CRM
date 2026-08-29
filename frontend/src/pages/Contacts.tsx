import React, { useState } from 'react';
import { useGetContactsQuery, useAddContactMutation, useUpdateContactMutation } from '../features/crm/crmApi';
import { PlusIcon, XMarkIcon } from '@heroicons/react/24/outline';

const Contacts: React.FC = () => {
  const { data: contacts, isLoading, error, refetch } = useGetContactsQuery({});
  const [addContact, { isLoading: isAdding }] = useAddContactMutation();
  const [updateContact, { isLoading: isUpdating }] = useUpdateContactMutation();
  
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingId, setEditingId] = useState<string | null>(null);

  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');

  const openAddModal = () => {
      setEditingId(null);
      setFirstName(''); setLastName(''); setEmail(''); setPhone('');
      setIsModalOpen(true);
  };

  const openEditModal = (contact: any) => {
      setEditingId(contact.id);
      setFirstName(contact.firstName || '');
      setLastName(contact.lastName || '');
      setEmail(contact.email || '');
      setPhone(contact.phone || '');
      setIsModalOpen(true);
  };

  const handleAddSubmit = async (e: React.FormEvent) => {
      e.preventDefault();
      try {
          if (editingId) {
              await updateContact({ id: editingId, firstName, lastName, email, phone }).unwrap();
          } else {
              await addContact({ firstName, lastName, email, phone }).unwrap();
          }
          setIsModalOpen(false);
          refetch();
      } catch (e) {
          console.error("Failed to save contact", e);
          alert("Failed to save contact");
      }
  };

  if (isLoading) return <div className="p-8 text-center text-gray-500">Loading contacts...</div>;
  if (error) return <div className="p-8 text-center text-red-500">Error loading contacts</div>;

  return (
    <div className="bg-white rounded-xl shadow-sm ring-1 ring-gray-900/5">
      <div className="px-4 py-6 sm:px-6 lg:px-8 border-b border-gray-200 flex justify-between items-center">
        <div>
          <h2 className="text-xl font-bold leading-7 text-gray-900 sm:truncate sm:tracking-tight">Contacts</h2>
          
        </div>
        <div>
            <button type="button" onClick={openAddModal} className="inline-flex items-center gap-x-2 rounded-md bg-indigo-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
                <PlusIcon className="-ml-0.5 h-5 w-5" aria-hidden="true" />
                Add Contact
            </button>
        </div>
      </div>
      <div className="flow-root">
        <div className="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
          <div className="inline-block min-w-full py-2 align-middle sm:px-6 lg:px-8">
            <table className="min-w-full divide-y divide-gray-200">
              <thead>
                <tr>
                  <th scope="col" className="py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 sm:pl-0">Name</th>
                  <th scope="col" className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Email</th>
                  <th scope="col" className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Company</th>
                  <th scope="col" className="relative py-3.5 pl-3 pr-4 sm:pr-0">
                    <span className="sr-only">Edit</span>
                  </th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-200 bg-white">
                {contacts?.map((contact: any) => (
                  <tr key={contact.id} className="hover:bg-gray-50 transition-colors">
                    <td className="whitespace-nowrap py-5 pl-4 pr-3 text-sm sm:pl-0">
                        <div className="flex items-center">
                            <div className="h-10 w-10 flex-shrink-0">
                                <div className="h-10 w-10 rounded-full bg-cyan-100 flex items-center justify-center border border-cyan-200 text-cyan-700 font-bold uppercase">
                                    {contact.firstName?.[0] || ''}{contact.lastName?.[0] || ''}
                                </div>
                            </div>
                            <div className="ml-4">
                                <div className="font-medium text-gray-900">{contact.firstName} {contact.lastName}</div>
                                <div className="text-gray-500 text-xs mt-0.5">ID: {contact.id.substring(0,8)}</div>
                            </div>
                        </div>
                    </td>
                    <td className="whitespace-nowrap px-3 py-5 text-sm text-gray-500">{contact.email}</td>
                    <td className="whitespace-nowrap px-3 py-5 text-sm text-gray-500">
                        <span className="inline-flex items-center rounded-md bg-gray-50 px-2 py-1 text-xs font-medium text-gray-600 ring-1 ring-inset ring-gray-500/10">
                            {contact.company?.name || 'N/A'}
                        </span>
                    </td>
                    <td className="relative whitespace-nowrap py-5 pl-3 pr-4 text-right text-sm font-medium sm:pr-0">
                      <button onClick={() => openEditModal(contact)} className="text-indigo-600 hover:text-indigo-900 pr-4 font-semibold">Edit</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>

      {isModalOpen && (
        <div className="fixed inset-0 z-50 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
          <div className="flex min-h-full items-center justify-center p-4 text-center sm:p-0">
            <div className="fixed inset-0 bg-gray-900/50 backdrop-blur-sm transition-opacity" onClick={() => setIsModalOpen(false)}></div>
            <div className="relative transform overflow-hidden rounded-xl bg-white px-4 pb-4 pt-5 text-left shadow-2xl transition-all sm:my-8 sm:w-full sm:max-w-lg sm:p-6">
              <div className="absolute top-0 right-0 pt-4 pr-4">
                <button type="button" className="bg-white rounded-md text-gray-400 hover:text-gray-500 focus:outline-none" onClick={() => setIsModalOpen(false)}>
                  <span className="sr-only">Close</span>
                  <XMarkIcon className="h-6 w-6" aria-hidden="true" />
                </button>
              </div>
              <div className="sm:flex sm:items-start">
                <div className="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left w-full">
                  <h3 className="text-lg leading-6 font-bold text-gray-900" id="modal-title">
                    {editingId ? 'Edit Contact' : 'Add New Contact'}
                  </h3>
                  <div className="mt-4">
                    <form onSubmit={handleAddSubmit} className="space-y-4">
                        <div className="grid grid-cols-2 gap-4">
                            <div>
                                <label className="block text-sm font-medium text-gray-700">First Name</label>
                                <input type="text" required value={firstName} onChange={(e) => setFirstName(e.target.value)} className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Last Name</label>
                                <input type="text" required value={lastName} onChange={(e) => setLastName(e.target.value)} className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                            </div>
                        </div>
                        <div className="grid grid-cols-2 gap-4">
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Email Address</label>
                                <input type="email" required value={email} onChange={(e) => setEmail(e.target.value)} className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Phone Number</label>
                                <input type="text" required value={phone} onChange={(e) => setPhone(e.target.value)} className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                            </div>
                        </div>
                        <div className="mt-5 sm:mt-4 sm:flex sm:flex-row-reverse">
                            <button type="submit" disabled={isAdding || isUpdating} className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:ml-3 sm:w-auto sm:text-sm disabled:opacity-50">
                            {(isAdding || isUpdating) ? 'Saving...' : 'Save Contact'}
                            </button>
                            <button type="button" onClick={() => setIsModalOpen(false)} className="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:w-auto sm:text-sm">
                            Cancel
                            </button>
                        </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Contacts;
