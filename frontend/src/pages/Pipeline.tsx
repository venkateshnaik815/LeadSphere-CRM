import React, { useState, useEffect } from 'react';
import { DragDropContext, Droppable, Draggable, type DropResult } from '@hello-pangea/dnd';
import { useGetOpportunitiesQuery, useUpdateOpportunityStageMutation, useAddOpportunityMutation, useDeleteOpportunityMutation } from '../features/crm/crmApi';
import { PlusIcon, XMarkIcon, TrashIcon } from '@heroicons/react/24/outline';

const stages = ['PROSPECTING', 'QUALIFICATION', 'PROPOSAL', 'NEGOTIATION', 'CLOSED_WON'];

const Pipeline: React.FC = () => {
  const { data: opportunities, isLoading, refetch } = useGetOpportunitiesQuery({});
  const [updateStage] = useUpdateOpportunityStageMutation();
  const [addOpportunity, { isLoading: isAdding }] = useAddOpportunityMutation();
  const [deleteOpportunity] = useDeleteOpportunityMutation();
  
  const [columns, setColumns] = useState<Record<string, any[]>>({});
  const [isModalOpen, setIsModalOpen] = useState(false);
  
  const [title, setTitle] = useState('');
  const [amount, setAmount] = useState('');
  const [stage, setStage] = useState('PROSPECTING');

  useEffect(() => {
    if (opportunities) {
      const newCols: Record<string, any[]> = {};
      stages.forEach(s => {
        newCols[s] = opportunities.filter((opp: any) => opp.stage === s);
      });
      setColumns(newCols);
    }
  }, [opportunities]);

  const onDragEnd = async (result: DropResult) => {
    const { source, destination, draggableId } = result;
    if (!destination) return;
    if (source.droppableId === destination.droppableId && source.index === destination.index) return;

    const startCol = columns[source.droppableId] || [];
    const finishCol = columns[destination.droppableId] || [];

    if (source.droppableId === destination.droppableId) {
        const newCol = Array.from(startCol);
        const [removed] = newCol.splice(source.index, 1);
        newCol.splice(destination.index, 0, removed);
        setColumns({ ...columns, [source.droppableId]: newCol });
    } else {
        const start = Array.from(startCol);
        const finish = Array.from(finishCol);
        const [removed] = start.splice(source.index, 1);
        finish.splice(destination.index, 0, removed);
        setColumns({
            ...columns,
            [source.droppableId]: start,
            [destination.droppableId]: finish
        });
        try {
            await updateStage({ id: draggableId, stage: destination.droppableId }).unwrap();
        } catch (e) {
            console.error("Failed to update stage", e);
        }
    }
  };

  const handleAddSubmit = async (e: React.FormEvent) => {
      e.preventDefault();
      try {
          await addOpportunity({
              title,
              amount: parseFloat(amount),
              stage,
              closeDate: new Date(new Date().setDate(new Date().getDate() + 30)).toISOString()
          }).unwrap();
          
          setIsModalOpen(false);
          setTitle('');
          setAmount('');
          setStage('PROSPECTING');
          refetch();
      } catch (e) {
          console.error("Failed to add opportunity", e);
          alert("Failed to add opportunity");
      }
  };

  const handleDelete = async (id: string, e: React.MouseEvent) => {
      e.stopPropagation();
      if (window.confirm("Are you sure you want to delete this opportunity?")) {
          try {
              await deleteOpportunity(id).unwrap();
              refetch();
          } catch (error) {
              console.error("Failed to delete opportunity", error);
              alert("Failed to delete opportunity");
          }
      }
  };

  if (isLoading) return <div className="p-8 text-center text-gray-500">Loading pipeline...</div>;

  return (
    <div className="h-full flex flex-col bg-gray-50/50 -m-4 sm:-m-6 lg:-m-8 p-4 sm:p-6 lg:p-8">
      <div className="flex justify-between items-center mb-8">
        <div>
          <h2 className="text-2xl font-black tracking-tight text-gray-900">Sales Pipeline</h2>
        </div>
        <button onClick={() => setIsModalOpen(true)} className="inline-flex items-center gap-x-2 rounded-lg bg-indigo-600 px-4 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 transition-colors">
          <PlusIcon className="-ml-0.5 h-5 w-5" aria-hidden="true" />
          Add Opportunity
        </button>
      </div>

      <DragDropContext onDragEnd={onDragEnd}>
        <div className="flex-1 flex gap-6 overflow-x-auto pb-4 custom-scrollbar">
          {stages.map(s => (
            <div key={s} className="w-80 flex-shrink-0 flex flex-col bg-gray-50 rounded-xl border border-gray-200 shadow-sm p-4">
              <div className="flex justify-between items-center mb-4">
                <h3 className="font-extrabold text-sm text-gray-700 tracking-wide bg-white px-3 py-1 rounded-md shadow-sm border border-gray-100 uppercase">{s.replace('_', ' ')}</h3>
                <span className="bg-gray-200 text-gray-600 font-bold text-xs px-2.5 py-1 rounded-full">{columns[s]?.length || 0}</span>
              </div>
              
              <Droppable droppableId={s}>
                {(provided, snapshot) => (
                  <div
                    ref={provided.innerRef}
                    {...provided.droppableProps}
                    className={`flex-1 overflow-y-auto custom-scrollbar transition-colors rounded-lg p-1 ${snapshot.isDraggingOver ? 'bg-indigo-50/50' : ''}`}
                  >
                    {columns[s]?.map((opp, index) => (
                      <Draggable key={opp.id} draggableId={opp.id} index={index}>
                        {(provided, snapshot) => (
                          <div
                            ref={provided.innerRef}
                            {...provided.draggableProps}
                            {...provided.dragHandleProps}
                            className={`group relative p-4 mb-3 rounded-xl shadow-sm border border-gray-200 bg-white transition-all ${
                              snapshot.isDragging ? 'rotate-2 scale-105 shadow-xl ring-2 ring-indigo-500 border-transparent z-50' : 'hover:border-indigo-300 hover:shadow-md'
                            }`}
                          >
                            <button 
                              onClick={(e) => handleDelete(opp.id, e)}
                              className="absolute top-2 right-2 p-1 text-gray-300 hover:text-red-500 hover:bg-red-50 rounded transition-colors opacity-0 group-hover:opacity-100"
                              title="Delete Opportunity"
                            >
                              <TrashIcon className="h-4 w-4" />
                            </button>
                            <div className="font-bold text-gray-900 mb-1 leading-tight pr-6">{opp.title}</div>
                            <div className="text-sm text-gray-500 mb-4">{opp.company?.name || 'No Company'}</div>
                            <div className="flex justify-between items-center mt-2 pt-3 border-t border-gray-100">
                              <span className="inline-flex items-center rounded-md bg-green-50 px-2 py-1 text-xs font-bold text-green-700 ring-1 ring-inset ring-green-600/20">
                                ${opp.amount?.toLocaleString()}
                              </span>
                              <span className="text-xs font-medium text-gray-400">
                                {opp.closeDate ? new Date(opp.closeDate).toLocaleDateString(undefined, {month: 'short', day: 'numeric'}) : ''}
                              </span>
                            </div>
                          </div>
                        )}
                      </Draggable>
                    ))}
                    {provided.placeholder}
                  </div>
                )}
              </Droppable>
            </div>
          ))}
        </div>
      </DragDropContext>

      {/* Add Opportunity Modal */}
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
                    Add New Opportunity
                  </h3>
                  <div className="mt-4">
                    <form onSubmit={handleAddSubmit} className="space-y-4">
                        <div>
                            <label className="block text-sm font-medium text-gray-700">Opportunity Title</label>
                            <input type="text" required value={title} onChange={(e) => setTitle(e.target.value)} className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" placeholder="e.g. Acme Corp License" />
                        </div>
                        <div className="grid grid-cols-2 gap-4">
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Amount ($)</label>
                                <input type="number" required value={amount} onChange={(e) => setAmount(e.target.value)} className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" placeholder="10000" />
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-gray-700">Stage</label>
                                <select value={stage} onChange={(e) => setStage(e.target.value)} className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                                    <option value="PROSPECTING">PROSPECTING</option>
                                    <option value="QUALIFICATION">QUALIFICATION</option>
                                    <option value="PROPOSAL">PROPOSAL</option>
                                    <option value="NEGOTIATION">NEGOTIATION</option>
                                </select>
                            </div>
                        </div>
                        <div className="mt-5 sm:mt-4 sm:flex sm:flex-row-reverse">
                            <button type="submit" disabled={isAdding} className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:ml-3 sm:w-auto sm:text-sm disabled:opacity-50">
                            {isAdding ? 'Saving...' : 'Save Opportunity'}
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

export default Pipeline;
