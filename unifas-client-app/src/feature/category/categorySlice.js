import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  addCategory,
  deleteCategory,
  editCategory,
  getCategoryList,
  getCategoryPage,
} from "../../api/categoryAPI";

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

export const getCategoriesPage = createAsyncThunk(
  "categoriesPage",
  async (page) => {
    const response = await getCategoryPage(page);
    return response.data;
  }
);

export const addNewCategory = createAsyncThunk("addCategory", async (data) => {
  const response = await addCategory(data);
  return response.data;
});

export const removeCategory = createAsyncThunk(
  "deleteCategory",
  async (categoryId) => {
    try {
      const response = await deleteCategory(categoryId);
      return {
        ...response.data,
        id: categoryId,
      };
    } catch (error) {
      throw new Error("Error removing category: " + error.message);
    }
  }
);

export const updateCategory = createAsyncThunk("editCategory", async (data) => {
  const response = await editCategory(data);
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
    setAddCategory: (state, action) => {
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
      // Page
      .addCase(getCategoriesPage.pending, (state) => {
        state.success = false;
        state.loading = true;
        state.error = false;
      })
      .addCase(getCategoriesPage.rejected, (state, action) => {
        state.success = false;
        state.loading = false;
        state.error = action.error;
      })
      .addCase(getCategoriesPage.fulfilled, (state, action) => {
        state.success = true;
        state.loading = false;
        state.value = action.payload;
        state.error = false;
      })
      // add category
      .addCase(addNewCategory.pending, (state) => {
        state.success = false;
        state.loading = true;
        state.error = false;
      })
      .addCase(addNewCategory.rejected, (state, action) => {
        state.success = false;
        state.loading = false;
        state.error = action.error;
      })
      .addCase(addNewCategory.fulfilled, (state, action) => {
        state.success = true;
        state.loading = false;
        state.value = action.payload;
        state.error = false;
      })
      // delete category
      .addCase(removeCategory.pending, (state) => {
        state.success = false;
        state.loading = true;
        state.error = false;
      })
      .addCase(removeCategory.rejected, (state, action) => {
        state.success = false;
        state.loading = false;
        state.error = action.error;
      })
      .addCase(removeCategory.fulfilled, (state, action) => {
        state.success = true;
        state.loading = false;
        state.value = action.payload;
        state.error = false;
      })
      // edit category
      .addCase(updateCategory.pending, (state) => {
        state.success = false;
        state.loading = true;
        state.error = false;
      })
      .addCase(updateCategory.rejected, (state, action) => {
        state.success = false;
        state.loading = false;
        state.error = action.error;
      })
      .addCase(updateCategory.fulfilled, (state, action) => {
        state.success = true;
        state.loading = false;
        state.value = action.payload;
        state.error = false;
      });
  },
});

export const {
  setCategoryLoading,
  setCategoryError,
  setCategorySuccess,
  setAddCategory,
} = categorySlice.actions;

export const selectCategoryLoading = (state) => state.category.loading;
export const selectCategoryError = (state) => state.category.error;
export const selectCategorySuccess = (state) => state.category.success;
export const selectCategories = (state) => state.category.values;
export const selectCategoriesPage = (state) => state.category.value;
export const selectAddCategories = (state) => state.category.value;
export const selectRemoveCategories = (state) => state.category.value;
export const selectUpdateCategories = (state) => state.category.value;

export const setLoadingTrueIfCalled = (isCalled) => (dispatch, getState) => {
  const currentValue = selectCategoryLoading(getState());
  if (currentValue === isCalled) {
    dispatch(setCategoryLoading(true));
  }
};

export default categorySlice.reducer;
