import axios from "axios";

const CART_API = "https://653785a5bb226bb85dd35c08.mockapi.io/api/v1/";

export const getCartItem = async () => {
  try {
    const response = await axios.get(`${CART_API}/carts`);
    console.log(response.data);
    return response;
  } catch (error) {
    throw error;
  }
};

export const updateCartItem = async (item) => {
  try {
    const response = await axios.put(`${CART_API}/carts/${item.id}`, item);
    console.log(response.data);
    return response;
  } catch (error) {
    throw error;
  }
};

export const deleteCartItem = async (cartItemId) => {
  try {
    const response = await axios.delete(`${CART_API}/carts/${cartItemId}`);
    console.log(response.data);
    return response;
  } catch (error) {
    throw error;
  }
};
