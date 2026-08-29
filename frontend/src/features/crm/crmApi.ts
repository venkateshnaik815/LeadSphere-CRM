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
  }),
});

export const { useGetLeadsQuery, useGetContactsQuery, useGetCompaniesQuery } = crmApi;
