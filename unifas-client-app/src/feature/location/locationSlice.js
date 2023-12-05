import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {getDistricts, getProvinces, getWards} from "../../api/locationAPI";


const initialState = {
    provinces : null,
    districts : null,
    wards : null
}
export const getProvincesList = createAsyncThunk("get-provinces", async (dispatch) => {
    const response = await getProvinces()
    return response.data;
})

export const getDistrictsList = createAsyncThunk("get-districs", async (dispatch) => {
    const response = await getDistricts()
    return response.data;
})

export const getWardsList = createAsyncThunk("get-wards", async (dispatch) => {
    const response = await getWards()
    return response.data;
})

export const locationSlice = createSlice({
    name : "location",
    initialState,
    reducers: {

    },
    extraReducers: builder => {
        builder
            .addCase(getProvincesList.fulfilled, (state,action) => {
            state.provinces = action.payload
            // console.log(state.provinces)
        })
            .addCase(getDistrictsList.fulfilled,(state,action) => {
                state.districts = action.payload
            })
            .addCase(getWardsList.fulfilled,(state,action) => {
                state.wards = action.payload
            })

    }
})


export const selectGetProvinces = (state) => state.location.provinces
export const selectGetDistricts = (state) => state.location.districts
export const selectGetWards = (state) => state.location.wards

export default locationSlice.reducer;