import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {addAddress, getAddress, getAddressForEdit, updateAddress} from "../../api/addressAPI";

const initialState = {
   loading : null,
   error : null,
   success : false,
   addressList: null,
   addressForEdit: null,
   updateAddress : null
}

export const getUserAddress = createAsyncThunk("get-address", async () =>{
   const response = await getAddress();
   return response;
})

export const getUserAddressForUpdate = createAsyncThunk("get-address-for-edit",async (id) =>{
   const response = await getAddressForEdit(id);
   return response
})

export const addUserAddress = createAsyncThunk("add-address",async (address) => {
   const response = await addAddress(address);
   return response;
})

export const updateUserAddress = createAsyncThunk("update-address", async (address,id) => {
   const response = await updateAddress(address,id)
   return response;
})


export const addressSlice = createSlice({
   name : "address",
   initialState,
   reducers: {
      setLoading : (state, action) => {
         state.loading = action.payload;
      },
      setError : (state, action) => {
         state.error = action.payload
      },
      setSuccess : (state, action) => {
         state.success = action.payload
      }
   },
   extraReducers: builder => {
      builder
          .addCase(getUserAddress.pending,(state) => {
             state.success = false;
             state.loading = true;
             state.error = false;
          })
          .addCase(getUserAddress.rejected,(state, action) => {
             state.succcess = false;
             state.loading = false;
             state.error = true;

          })
          .addCase(getUserAddress.fulfilled, (state, action) => {
             state.succcess = true;
             state.loading = false;
             state.error = false;
             state.addressList = action.payload?.data;
          })

          .addCase(getUserAddressForUpdate.pending,(state) => {
             state.success = false;
             state.loading = true;
             state.error = false;
          })
          .addCase(getUserAddressForUpdate.rejected,(state, action) => {
             state.succcess = false;
             state.loading = false;
             state.error = true;

          })
          .addCase(getUserAddressForUpdate.fulfilled, (state, action) => {
             state.succcess = true;
             state.loading = false;
             state.error = false;
             state.addressForEdit = action.payload?.data;
          })

          .addCase(addUserAddress.pending,(state) => {
             state.success = false;
             state.loading = true;
             state.error = false;
          })
          .addCase(addUserAddress.rejected,(state, action) => {
             state.succcess = false;
             state.loading = false;
             state.error = true;

          })
          .addCase(addUserAddress.fulfilled, (state, action) => {
             state.succcess = true;
             state.loading = false;
             state.error = false;
             state.addressList = action.payload?.data;
          })

          .addCase(updateUserAddress.pending,(state) => {
             state.success = false;
             state.loading = true;
             state.error = false;
          })
          .addCase(updateUserAddress.rejected,(state, action) => {
             state.succcess = false;
             state.loading = false;
             state.error = true;

          })
          .addCase(updateUserAddress.fulfilled, (state, action) => {
             state.succcess = true;
             state.loading = false;
             state.error = false;
             state.addressList = action.payload?.data;
          })

   }
})

export const {setLoading,setError,setSuccess} = addressSlice.actions;
export const selectLoading = (state) => state.address.loading;
export const selectError = (state) => state.address.error;
export const selectSuccess = (state) => state.address.success;
export const selectGetAddress = (state) => state.address.addressList;
export const selectGetAddressForUpdate = (state) => state.address.addressForEdit
export const selectUpdateAddress = (state) => state.address.updateAddress

export default addressSlice.reducer;