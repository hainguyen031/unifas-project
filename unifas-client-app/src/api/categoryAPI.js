import axios from "axios";
import { UNIFAS_API } from "../constants/api";

const CATEGORY_MANAGEMENT = `${UNIFAS_API}/categories`

export const getCategoryList = async () => {
    let result = null;
    try {
        result = await axios.get(`${CATEGORY_MANAGEMENT}`);
    }
    catch (e) {
        console.log("Find Categories API error: " + e);
    }
    return result;
};

// export const findStaff = async (staffId) => {
//     let result = null;
//     try {
//         result = await axios.get(`${STAFF_MANAGEMENT_API}/staffs/${staffId}`);
//     }
//     catch (e) {
//         console.log("Find staff API error: " + e);
//     }
//     return result;
// };

// export const createStaff = async (staff) => {
//     let result = null;
//     try {
//         result = await axios.post(`${STAFF_MANAGEMENT_API}/staffs`, staff);
//     }
//     catch (e) {
//         console.log("Find staff API error: " + e);
//     }
//     return result;
// };

// export const updateStaff = async (staff) => {
//     let result = null;
//     try {
//         result = await axios.put(`${STAFF_MANAGEMENT_API}/staffs/${staff.id}`, staff);
//     }
//     catch (e) {
//         console.log("Update staff API error: " + e);
//     }
//     return result;
// };

// export const deleteStaff = async (staffId) => {
//     let result = null;
//     try {
//         result = await axios.delete(`${STAFF_MANAGEMENT_API}/staffs/${staffId}`);
//     }
//     catch (e) {
//         console.log("Delete staff API eroor: " + e);
//     }
//     return result;
// };