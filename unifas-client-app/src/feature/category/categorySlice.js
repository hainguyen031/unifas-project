import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getCategoryList } from "../../api/categoryAPI";

const initialState = {
    values: null,
    value: null,
    loading: false,
    error: null,
    success: false,
};

export const getCategories = createAsyncThunk("categories", async () => {
    const response = await getCategoryList();
    return response.data;
});

export const categorySlice = createSlice({
    name: "category",
    initialState,
    reducers: {
        setCategoryLoading: (state, action) => {
            state.loading = action.payload;
        },

        setCategoryError: (state, action) => {
            state.error = action.payload;
        },

        setCategorySuccess: (state, action) => {
            state.success = action.payload;
        },
    },

    extraReducers: (builder) => {
        builder
        // List
        .addCase(getCategories.pending, (state) => {
            state.success = false;
            state.loading = true;
            state.error = false;
        })
        .addCase(getCategories.rejected, (state, action) => {
            state.success = false;
            state.loading = false;
            state.error = action.error;
        })
        .addCase(getCategories.fulfilled, (state, action) => {
            state.success = true;
            state.loading = false;
            state.values = action.payload;
            state.error = false;
        })
    },
});

export const {
    setCategoryLoading,
    setCategoryError,
    setCategorySuccess
} = categorySlice.actions;

export const selectCategoryLoading = (state) => state.category.loading;
export const selectCategoryError = (state) => state.category.error;
export const selectCategorySuccess = (state) => state.category.success;
export const selectCategories = (state) => state.category.values;

export const setLoadingTrueIfCalled = (isCalled) => (dispatch, getState) => {
    const currentValue = selectCategoryLoading(getState());
    if (currentValue === isCalled) {
        dispatch(setCategoryLoading(true));
    }
};

export default categorySlice.reducer;