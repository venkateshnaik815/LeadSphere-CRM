import React from 'react';
import { useGetLeadsQuery, useGetOpportunitiesQuery, useGetCompaniesQuery } from '../features/crm/crmApi';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';
import { UserGroupIcon, ChartBarIcon, CurrencyDollarIcon, BuildingOfficeIcon } from '@heroicons/react/24/outline';

const Dashboard: React.FC = () => {
    const { data: leads } = useGetLeadsQuery({});
    const { data: opportunities } = useGetOpportunitiesQuery({});
    const { data: companies } = useGetCompaniesQuery({});

    const totalPipelineValue = opportunities?.reduce((acc: number, curr: any) => acc + (curr.amount || 0), 0) || 0;
    
    const stageData = [
        { name: 'Prospecting', count: opportunities?.filter((o: any) => o.stage === 'PROSPECTING').length || 0 },
        { name: 'Qualification', count: opportunities?.filter((o: any) => o.stage === 'QUALIFICATION').length || 0 },
        { name: 'Proposal', count: opportunities?.filter((o: any) => o.stage === 'PROPOSAL').length || 0 },
        { name: 'Negotiation', count: opportunities?.filter((o: any) => o.stage === 'NEGOTIATION').length || 0 },
        { name: 'Won', count: opportunities?.filter((o: any) => o.stage === 'CLOSED_WON').length || 0 }
    ];

    const stats = [
        { name: 'Total Leads', value: leads?.length || 0, icon: UserGroupIcon, color: 'text-blue-600', bg: 'bg-blue-100' },
        { name: 'Total Opportunities', value: opportunities?.length || 0, icon: ChartBarIcon, color: 'text-indigo-600', bg: 'bg-indigo-100' },
        { name: 'Pipeline Value', value: `$${totalPipelineValue.toLocaleString()}`, icon: CurrencyDollarIcon, color: 'text-green-600', bg: 'bg-green-100' },
        { name: 'Total Companies', value: companies?.length || 0, icon: BuildingOfficeIcon, color: 'text-purple-600', bg: 'bg-purple-100' },
    ];

    return (
        <div className="space-y-6">
            <div>
                <h1 className="text-2xl font-bold text-gray-900">Dashboard Overview</h1>
                <p className="mt-1 text-sm text-gray-500">Welcome back! Here's what's happening with your sales today.</p>
            </div>

            <div className="grid grid-cols-1 gap-5 sm:grid-cols-2 lg:grid-cols-4">
                {stats.map((item) => (
                    <div key={item.name} className="relative overflow-hidden rounded-xl bg-white px-4 pt-5 pb-12 shadow-sm ring-1 ring-gray-900/5 sm:px-6 sm:pt-6">
                        <dt>
                            <div className={`absolute rounded-md p-3 ${item.bg}`}>
                                <item.icon className={`h-6 w-6 ${item.color}`} aria-hidden="true" />
                            </div>
                            <p className="ml-16 truncate text-sm font-medium text-gray-500">{item.name}</p>
                        </dt>
                        <dd className="ml-16 flex items-baseline pb-6 sm:pb-7">
                            <p className="text-2xl font-semibold text-gray-900">{item.value}</p>
                        </dd>
                    </div>
                ))}
            </div>

            <div className="grid grid-cols-1 gap-5 lg:grid-cols-2">
                <div className="rounded-xl bg-white p-6 shadow-sm ring-1 ring-gray-900/5">
                    <h2 className="text-lg font-medium text-gray-900 mb-4">Pipeline by Stage</h2>
                    <div className="h-72">
                        <ResponsiveContainer width="100%" height="100%">
                            <BarChart data={stageData} margin={{ top: 10, right: 10, left: -20, bottom: 0 }}>
                                <CartesianGrid strokeDasharray="3 3" vertical={false} />
                                <XAxis dataKey="name" axisLine={false} tickLine={false} />
                                <YAxis axisLine={false} tickLine={false} />
                                <Tooltip cursor={{fill: 'transparent'}} contentStyle={{borderRadius: '8px', border: 'none', boxShadow: '0 4px 6px -1px rgb(0 0 0 / 0.1)'}} />
                                <Bar dataKey="count" fill="#4f46e5" radius={[4, 4, 0, 0]} barSize={40} />
                            </BarChart>
                        </ResponsiveContainer>
                    </div>
                </div>

                <div className="rounded-xl bg-white p-6 shadow-sm ring-1 ring-gray-900/5">
                    <h2 className="text-lg font-medium text-gray-900 mb-4">Recent Opportunities</h2>
                    <div className="flow-root">
                        <ul role="list" className="-my-5 divide-y divide-gray-100">
                            {opportunities?.slice(0, 5).map((opp: any) => (
                                <li key={opp.id} className="py-4 flex items-center justify-between">
                                    <div className="flex flex-col">
                                        <p className="text-sm font-semibold text-gray-900">{opp.title}</p>
                                        <p className="text-sm text-gray-500">{opp.company?.name || 'N/A'}</p>
                                    </div>
                                    <div className="flex flex-col items-end">
                                        <p className="text-sm font-medium text-gray-900">${opp.amount?.toLocaleString()}</p>
                                        <span className="inline-flex items-center rounded-full bg-indigo-50 px-2 py-1 text-xs font-medium text-indigo-700 ring-1 ring-inset ring-indigo-700/10">
                                            {opp.stage.replace('_', ' ')}
                                        </span>
                                    </div>
                                </li>
                            ))}
                            {(!opportunities || opportunities.length === 0) && (
                                <li className="py-4 text-sm text-gray-500">No opportunities found.</li>
                            )}
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
