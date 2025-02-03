import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { TOUR_SERVICE_BASE_URL } from "../constants/urls";
import {
  isJsonContentType,
  processError,
  processResponse,
} from "../utils/requestutils";
import { httpGet } from "../constants/http";

export const tourAPI = createApi({
  reducerPath: "tourAPI",
  baseQuery: fetchBaseQuery({
    baseUrl: `${TOUR_SERVICE_BASE_URL}/tour`,
    credentials: "include",
    isJsonContentType,
  }),
  tagTypes: ["tour"],
  endpoints: (builder) => ({
    fetchAllToursByTourSubCategoryId: builder.query({
      query: (tourSubCategoryId) => ({
        url: `?tourSubcategoryId=${tourSubCategoryId}`,
        method: httpGet,
      }),
      keepUnusedDataFor: 120,
      transformResponse: processResponse,
      transformErrorResponse: processError,
      providesTags: () => ["tour"],
    })
  }),
});
