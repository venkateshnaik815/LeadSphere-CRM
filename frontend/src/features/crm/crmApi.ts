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
  }),
});

export const { 
    useGetLeadsQuery, 
    useGetContactsQuery, 
    useGetCompaniesQuery,
    useGetOpportunitiesQuery,
    useUpdateOpportunityStageMutation
} = crmApi;
