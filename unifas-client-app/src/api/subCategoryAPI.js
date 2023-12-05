import axios from "axios";
import { UNIFAS_API } from "../constants/api";

const SUBCATEGORY_MANAGEMENT = `${UNIFAS_API}/subCategories`;

export const getSubCategoryList = async () => {
    let result = null;
    try {
        result = await axios.get(`${SUBCATEGORY_MANAGEMENT}`);
    }
    catch (e) {
        console.log("Find SubCategories API error: " + e);
    }
    return result;
};