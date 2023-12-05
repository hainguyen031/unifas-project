import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { getProductList } from "../../api/productAPI";

const initialState = {
  values: null,
  loading: false,
  error: null,
  success: false,
};

export const getAllProducts = createAsyncThunk("products/list", async () => {
  try {
    const response = await getProductList();
    return response.data;
  } catch (error) {
    console.log(error);
    throw error;
  }
});

const productsSlice = createSlice({
  name: "products",
  initialState,
  reducers: {
    setLoading: (state, action) => {
      state.loading = action.payload;
    },
    setError: (state, action) => {
      state.error = action.payload;
    },
    setSuccess: (state, action) => {
      state.success = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(getAllProducts.pending, (state) => {
        state.success = false;
        state.loading = true;
        state.error = false;
      })
      .addCase(getAllProducts.rejected, (state, action) => {
        state.success = false;
        state.loading = false;
        state.error = action.error.message;
      })
      .addCase(getAllProducts.fulfilled, (state, action) => {
        state.success = true;
        state.loading = false;
        state.values = action.payload;
        state.error = false;
      });
  },
});

export const { setLoading, setError, setSuccess } = productsSlice.actions;

export const selectLoading = (state) => state.products.loading;
export const selectError = (state) => state.products.error;
export const selectSuccess = (state) => state.products.success;
export const selectProductList = (state) => state.products.values;

export default productsSlice.reducer;
