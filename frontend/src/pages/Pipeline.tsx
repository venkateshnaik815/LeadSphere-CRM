import React, { useState, useEffect } from 'react';
import { DragDropContext, Droppable, Draggable, DropResult } from '@hello-pangea/dnd';
import { useGetOpportunitiesQuery, useUpdateOpportunityStageMutation } from '../features/crm/crmApi';

const stages = ['PROSPECTING', 'QUALIFICATION', 'PROPOSAL', 'NEGOTIATION', 'CLOSED_WON', 'CLOSED_LOST'];

const Pipeline: React.FC = () => {
  const { data: opportunities, isLoading } = useGetOpportunitiesQuery({});
  const [updateStage] = useUpdateOpportunityStageMutation();
  
  const [columns, setColumns] = useState<Record<string, any[]>>({});

  useEffect(() => {
    if (opportunities) {
      const newCols: Record<string, any[]> = {};
      stages.forEach(stage => {
        newCols[stage] = opportunities.filter((opp: any) => opp.stage === stage);
      });
      setColumns(newCols);
    }
  }, [opportunities]);

  const onDragEnd = async (result: DropResult) => {
    const { source, destination, draggableId } = result;

    if (!destination) return;

    if (source.droppableId === destination.droppableId && source.index === destination.index) {
      return;
    }

    const startCol = columns[source.droppableId];
    const finishCol = columns[destination.droppableId];

    if (source.droppableId === destination.droppableId) {
        // Reordering in same column (local state only for now)
        const newCol = Array.from(startCol);
        const [removed] = newCol.splice(source.index, 1);
        newCol.splice(destination.index, 0, removed);
        setColumns({ ...columns, [source.droppableId]: newCol });
    } else {
        // Moving to new column
        const start = Array.from(startCol);
        const finish = Array.from(finishCol);
        const [removed] = start.splice(source.index, 1);
        finish.splice(destination.index, 0, removed);

        setColumns({
            ...columns,
            [source.droppableId]: start,
            [destination.droppableId]: finish
        });

        // Update Backend
        try {
            await updateStage({ id: draggableId, stage: destination.droppableId }).unwrap();
        } catch (e) {
            console.error("Failed to update stage", e);
        }
    }
  };

  if (isLoading) return <div>Loading pipeline...</div>;

  return (
    <div className="h-full flex flex-col">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-semibold">Sales Pipeline</h1>
        <button className="bg-indigo-600 text-white px-4 py-2 rounded-md hover:bg-indigo-700">Add Opportunity</button>
      </div>

      <DragDropContext onDragEnd={onDragEnd}>
        <div className="flex space-x-4 overflow-x-auto pb-4 h-full">
          {stages.map(stage => (
            <div key={stage} className="bg-gray-100 rounded-lg p-4 min-w-[300px] flex flex-col max-h-full">
              <h2 className="font-semibold text-gray-700 mb-4">{stage.replace('_', ' ')}</h2>
              
              <Droppable droppableId={stage}>
                {(provided) => (
                  <div
                    {...provided.droppableProps}
                    ref={provided.innerRef}
                    className="flex-1 overflow-y-auto"
                  >
                    {columns[stage]?.map((opp, index) => (
                      <Draggable key={opp.id} draggableId={opp.id} index={index}>
                        {(provided) => (
                          <div
                            ref={provided.innerRef}
                            {...provided.draggableProps}
                            {...provided.dragHandleProps}
                            className="bg-white p-4 rounded shadow-sm mb-3 border border-gray-200 cursor-grab"
                          >
                            <h3 className="font-medium text-gray-900">{opp.title}</h3>
                            <p className="text-sm text-gray-500 mt-1">${opp.amount.toLocaleString()}</p>
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
    </div>
  );
};

export default Pipeline;
