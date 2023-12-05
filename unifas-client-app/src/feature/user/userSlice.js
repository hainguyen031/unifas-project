import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { changePassword, forgetPassword } from "../../api/userAPI";

const initialState = {
  value: null,
  loading: false,
  error: null,
  success: false,
  successChangePass: false
};

export const forgotPasswordUser = createAsyncThunk(
  "forgotPasswordUser",
  async (emailData) => {
    const response = await forgetPassword(emailData);
    return response.data;
  }
);

export const changePasswordUser = createAsyncThunk(
  "changePasswordUser",
  async (data) => {
    const response = await changePassword(data);
    return response.data;
  }
);

export const userSlice = createSlice({
  name: "userAccount",
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
    setSuccessChangePass: (state, action) => {
      state.successChangePass = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(forgotPasswordUser.pending, (state) => {
        state.success = false;
        state.loading = true;
        state.error = false;
      })
      .addCase(forgotPasswordUser.rejected, (state, action) => {
        state.success = false;
        state.loading = false;
        state.error = action.error;
      })
      .addCase(forgotPasswordUser.fulfilled, (state, action) => {
        state.success = true;
        state.loading = false;
        state.error = false;
        state.value = action.payload;
      })

      .addCase(changePasswordUser.pending, (state) => {
        state.successChangePass = false;
        state.loading = true;
        state.error = false;
      })
      .addCase(changePasswordUser.rejected, (state, action) => {
        state.successChangePass = false;
        state.loading = false;
        state.error = action.error;
      })
      .addCase(changePasswordUser.fulfilled, (state, action) => {
        state.successChangePass = true;
        state.loading = false;
        state.error = false;
        state.value = action.payload;
      });
  },
});

export const { setLoading, setError, setSuccess, setSuccessChangePass } = userSlice.actions;

export const selectLoading = (state) => state.userAccount.loading;
export const selectError = (state) => state.userAccount.error;
export const selectSuccess = (state) => state.userAccount.success;
export const selectSuccessChangePass = (state) => state.userAccount.successChangePass;
export const selectUserCodePass = (state) => state.userAccount.value;
export const selectUserChangePass = (state) => state.userAccount.value;


export default userSlice.reducer;
