import axios from "axios";
import { toast } from "react-toastify";
const UNIFAS_API = `http://localhost:8080/api/auth`;

export const forgetPassword = async (emailData) => {
  let result = null;
  try {
    result = await axios({
      method: "post",
      url: `${UNIFAS_API}` + "/forget-password",
      data: {
        email: emailData.email,
      },
    });
  } catch (e) {
    console.error("forgetPassword API error: " + e);
  }
  return result;
};

export const changePassword = async (data) => {
  let result = null;
  try {
      result = await axios({
        method: "post",
        url: `${UNIFAS_API}` + "/change-password",
        data: {
          newPass: data.password,
          email: data.email,
        },
      });
  } catch (e) {
    console.error("changePassword API error: " + e);
  }
  return result;
};
