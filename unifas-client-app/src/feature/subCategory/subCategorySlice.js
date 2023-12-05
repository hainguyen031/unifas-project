import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getSubCategoryList } from "../../api/subCategoryAPI";

const initialState = {
    values: null,
    value: null,
    loading: false,
    error: null,
    success: false,
};

export const getSubCategories = createAsyncThunk("subCategories", async () => {
    const response = await getSubCategoryList();
    return response.data;
});

export const subCategorySlice = createSlice({
    name: "subCategory",
    initialState,
    reducers: {
        setSubCategoryLoading: (state, action) => {
            state.loading = action.payload;
        },

        setSubCategoryError: (state, action) => {
            state.error = action.payload;
        },

        setSubCategorySuccess: (state, action) => {
            state.success = action.payload;
        },
    },

    extraReducers: (builder) => {
        builder
        // List
        .addCase(getSubCategories.pending, (state) => {
            state.success = false;
            state.loading = true;
            state.error = false;
        })
        .addCase(getSubCategories.rejected, (state, action) => {
            state.success = false;
            state.loading = false;
            state.error = action.error;
        })
        .addCase(getSubCategories.fulfilled, (state, action) => {
            state.success = true;
            state.loading = false;
            state.values = action.payload;
            state.error = false;
        })
    },
});

export const {
    setSubCategoryLoading,
    setSubCategoryError,
    setSubCategorySuccess
} = subCategorySlice.actions;

export const selectSubCategoryLoading = (state) => state.subCategory.loading;
export const selectSubCategoryError = (state) => state.subCategory.error;
export const selectSubCategorySuccess = (state) => state.subCategory.success;
export const selectSubCategories = (state) => state.subCategory.values;

export const setLoadingTrueIfCalled = (isCalled) => (dispatch, getState) => {
    const currentValue = selectSubCategoryLoading(getState());
    if (currentValue === isCalled) {
        dispatch(setSubCategoryLoading(true));
    }
};

export default subCategorySlice.reducer;