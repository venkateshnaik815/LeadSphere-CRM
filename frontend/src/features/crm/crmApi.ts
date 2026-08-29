import { api } from '../../services/api';

export const crmApi = api.injectEndpoints({
  endpoints: (builder) => ({
    getLeads: builder.query({
      query: () => '/leads',
      providesTags: ['Lead'],
    }),
    getContacts: builder.query({
      query: () => '/contacts',
      providesTags: ['Contact'],
    }),
    getCompanies: builder.query({
      query: () => '/companies',
      providesTags: ['Company'],
    }),
    getOpportunities: builder.query({
      query: () => '/opportunities',
      providesTags: ['Opportunity'],
    }),
    updateOpportunityStage: builder.mutation({
      query: ({ id, stage }) => ({
        url: `/opportunities/${id}/stage`,
        method: 'PATCH',
        body: stage,
        headers: {
            'Content-Type': 'text/plain'
        }
      }),
      invalidatesTags: ['Opportunity'],
    }),
    addOpportunity: builder.mutation({
      query: (opportunity) => ({
        url: '/opportunities',
        method: 'POST',
        body: opportunity,
      }),
      invalidatesTags: ['Opportunity'],
    }),
    addLead: builder.mutation({
      query: (lead) => ({
        url: '/leads',
        method: 'POST',
        body: lead,
      }),
      invalidatesTags: ['Lead'],
    }),
    addContact: builder.mutation({
      query: (contact) => ({
        url: '/contacts',
        method: 'POST',
        body: contact,
      }),
      invalidatesTags: ['Contact'],
    }),
    addCompany: builder.mutation({
      query: (company) => ({
        url: '/companies',
        method: 'POST',
        body: company,
      }),
      invalidatesTags: ['Company'],
    }),
    updateLead: builder.mutation({
      query: ({ id, ...lead }) => ({
        url: `/leads/${id}`,
        method: 'PUT',
        body: lead,
      }),
      invalidatesTags: ['Lead'],
    }),
    updateContact: builder.mutation({
      query: ({ id, ...contact }) => ({
        url: `/contacts/${id}`,
        method: 'PUT',
        body: contact,
      }),
      invalidatesTags: ['Contact'],
    }),
    updateCompany: builder.mutation({
      query: ({ id, ...company }) => ({
        url: `/companies/${id}`,
        method: 'PUT',
        body: company,
      }),
      invalidatesTags: ['Company'],
    }),
    deleteOpportunity: builder.mutation({
      query: (id) => ({
        url: `/opportunities/${id}`,
        method: 'DELETE',
      }),
      invalidatesTags: ['Opportunity'],
    }),
  }),
});

export const { 
    useGetLeadsQuery, 
    useGetContactsQuery, 
    useGetCompaniesQuery,
    useGetOpportunitiesQuery,
    useUpdateOpportunityStageMutation,
    useAddOpportunityMutation,
    useAddLeadMutation,
    useAddContactMutation,
    useAddCompanyMutation,
    useUpdateLeadMutation,
    useUpdateContactMutation,
    useUpdateCompanyMutation,
    useDeleteOpportunityMutation
} = crmApi;
