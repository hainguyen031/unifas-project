import axios from "axios";


const GET_ALL_PROVINCES_API = "https://provinces.open-api.vn/api/p"
const GET_ALL_DISTRICTS_API = "https://provinces.open-api.vn/api/d/"
const GET_ALL_WARDS_API = "https://provinces.open-api.vn/api/w/"

export const getProvinces = async () => {
    try {
        let provincesList = await axios.get(GET_ALL_PROVINCES_API)
        return provincesList;
    }catch (e){
        console.log("Get provinces fail. Error: " + e)
    }
}

export const getDistricts = async () => {
    try {
        let districtsList = await axios.get(GET_ALL_DISTRICTS_API)
        return districtsList;
    }catch (e){
        console.log("Get districts fail. Error: " + e)
    }
}

export const getWards = async () => {
    try {
        let wardsList = await axios.get(GET_ALL_WARDS_API)
        return wardsList;
    }catch (e){
        console.log("Get wards fail. Error: " + e)
    }
}
