import axios from "axios";
import { UNIFAS_API } from "../constants/api";

const CATEGORY_MANAGEMENT = `${UNIFAS_API}/categories`;

export const getCategoryList = async () => {
  let result = null;
  try {
    result = await axios.get(`${CATEGORY_MANAGEMENT}`);
  } catch (e) {
    console.log("Find Categories API error: " + e);
  }
  return result;
};

export const getCategoryPage = async (page) => {
  let result = null;
  try {
    result = await axios.get(`${CATEGORY_MANAGEMENT}/page`, {
      params: {
        page: page,
      },
    });
  } catch (e) {
    console.error("Error to get category page: " + e);
  }
  return result;
};

export const addCategory = async (data) => {
  let result = null;
  try {
    result = await axios({
      method: "post",
      url: `${CATEGORY_MANAGEMENT}`,
      data: {
        name: data.name,
        gender: data.gender,
      },
    });
  } catch (e) {
    console.error("add catogory API error: " + e);
  }
  return result;
};
