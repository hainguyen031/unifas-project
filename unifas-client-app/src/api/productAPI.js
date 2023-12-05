import axios from "axios";

const PRODUCT_API = "https://653785a5bb226bb85dd35c08.mockapi.io/api/v1";

export const getProductList = async () => {
  try {
    const response = await axios.get(`${PRODUCT_API}/products`);
    console.log(response.data);
    return response;
  } catch (error) {
    throw error;
  }
};

