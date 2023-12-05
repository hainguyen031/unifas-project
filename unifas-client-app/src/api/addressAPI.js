import {UNIFAS_API} from "../constants/api";
import axios from "axios";

const userId = localStorage.getItem("id")
const GET_ADDRESSES_API = `${UNIFAS_API}/addresses/${userId}`
const ADD_ADDRESS_API = `${UNIFAS_API}/address/${userId}`
const GET_ADDRESS_FOR_EDIT_API = `${UNIFAS_API}/address`
const UPDATE_ADDRESS_API = `${UNIFAS_API}/user/${userId}/address`

export const getAddress = async () => {
    try {
        let addressList = await axios.get(GET_ADDRESSES_API)
        return addressList?.data;
    }catch (e){
        console.log("Get address fail. Error: " + e)
    }
}

export  const getAddressForEdit = async (addressId) => {
    try {
        let address = await axios.get(`${GET_ADDRESS_FOR_EDIT_API}/${addressId}`)
        return address?.data;
    }catch (e){
        console.log("Get address for edit fail. Error: " + e)
    }
}

export const addAddress = async (address) => {
    const token = "Bearer " + localStorage.getItem("token")
    let result= null;
    try {
        result =await axios.post(ADD_ADDRESS_API,address,{
            headers: {
                Authorization : token,
            }
        })
    }catch (e){
        console.log("Add address fail. Error: " + e)
    }
    return result?.data;
}

export const updateAddress = async (address,addressId) => {
    try {
        let response = await axios.put(`${UPDATE_ADDRESS_API}/${addressId}`,address)
        return response.data
    }catch (e){
        console.log("Add address fail. Error: " + e)
    }
}