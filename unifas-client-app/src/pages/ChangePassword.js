import { useFormik } from "formik";
import { Link, useNavigate } from "react-router-dom";
import * as Yup from "yup";
import {
  changePasswordUser,
  selectError,
  selectLoading,
  selectSuccessChangePass,
  setError,
  setSuccessChangePass,
} from "../feature/user/userSlice";
import { ToastContainer, toast } from "react-toastify";
import { useDispatch, useSelector } from "react-redux";
import { useEffect, useState } from "react";

function ChangePassword() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const success = useSelector(selectSuccessChangePass);
  const pending = useSelector(selectLoading);
  const error = useSelector(selectError);

  const getEmailStorageWithExpiry = (key) => {
    const itemStr = localStorage.getItem(key);
    if (!itemStr) {
      return null;
    }
    const item = JSON.parse(itemStr);
    const now = new Date();
    if (now.getTime() > item.expiredTime) {
      localStorage.removeItem(key);
      return null;
    }
    return item.verificationCodes.email;
  };

  const formik = useFormik({
    initialValues: {
      email: getEmailStorageWithExpiry("codePass"),
      password: "",
      confirmPassword: "",
      verificationCodes: "",
    },
    validationSchema: Yup.object({
      email: Yup.string()
        .email("Vui lòng nhập một địa chỉ email hợp lệ.")
        .required("Vui lòng nhập một địa chỉ email.")
        .matches(
          /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/,
          "Vui lòng nhập một địa chỉ email hợp lệ."
        ),
      password: Yup.string()
        .required("Mật khẩu mới là bắt buộc.")
        .matches(
          /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()])(?=.*[A-Z])[A-Za-z\d!@#$%^&*()]{8,}$/,
          "Mật khẩu phải có ít nhất 8 ký tự, một chữ cái, một số vá một ký tự đặc biệt"
        ),
      confirmPassword: Yup.string()
        .oneOf(
          [Yup.ref("password"), null],
          "Mật khẩu xác nhận phải trùng khớp."
        )
        .required("Xác nhận mật khẩu là bắt buộc."),
      verificationCodes: Yup.string().required("Mã xác thực là bắt buộc."),
    }),
  });

  const [data, setData] = useState({
    email: getEmailStorageWithExpiry("codePass"),
    password: "",
    verificationCodes: "",
  });

  useEffect(() => {
    if (success) {
      toast.success("Đổi mật khẩu thành công !", {
        position: toast.POSITION.TOP_RIGHT,
        type: toast.TYPE.SUCCESS,
      });
      dispatch(setSuccessChangePass(false));
      setTimeout(() => {
        navigate("/");
      }, 5000);
    } else if (error) {
      toast.error(
        "Đổi mật khẩu thất bại, hãy kiểm tra lại địa chỉ Email của bạn !",
        {
          position: toast.POSITION.TOP_RIGHT,
          type: toast.TYPE.ERROR,
        }
      );
      dispatch(setError(null));
    }
  }, [success, error]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const getLocalStorageWithExpiry = (key) => {
    const itemStr = localStorage.getItem(key);
    if (!itemStr) {
      return null;
    }
    const item = JSON.parse(itemStr);
    const now = new Date();
    if (now.getTime() > item.expiredTime) {
      return "OutExp";
    }
    return item.verificationCodes.codePass;
  };


  const handleFormSubmit = async (e) => {
    e.preventDefault();
    let checkCode = getLocalStorageWithExpiry("codePass");
    if (checkCode === formik.values.verificationCodes) {
      dispatch(changePasswordUser(formik.values));
      dispatch(setSuccessChangePass(false));
      localStorage.removeItem("codePass");
    } else if(checkCode ==="OutExp"){
      toast.error("Mã xác thực hết hạn !", {
        position: toast.POSITION.TOP_RIGHT,
        type: toast.TYPE.ERROR,
      });
    } else{
      toast.error("Mã xác thực sai !", {
        position: toast.POSITION.TOP_RIGHT,
        type: toast.TYPE.ERROR,
      });
      } 
  };

  return (
    <div className="container" style={{ margin: "0 145px" }}>
      <div style={{ width: "auto", marginBottom: 52 }}>
        <h2 style={{ marginTop: 52 }}>
          <span className="title">Đặt lại mật khẩu của bạn</span>
        </h2>
      </div>
      <div
        style={{
          border: "1px solid rgb(224, 224, 224)",
          padding: "28px 20px",
          width: 710,
          height: "fit-content",
        }}
      >
        <div className="fr-contents-card full">
          <div>
            <div
              style={{
                display: "flex",
                position: "relative",
                // marginBottom: 30,
              }}
            >
              <div>Vui lòng nhập mật khẩu mới</div>
            </div>
            <form onSubmit={handleFormSubmit}>
              <div className=" row mb-3">
                <div className="col-8">
                  <input
                    type="hidden"
                    id="email"
                    name="email"
                    value={getEmailStorageWithExpiry("codePass")}
                    style={{ width: "100%" }}
                    disabled
                  ></input>
                </div>
              </div>
              <div className=" row mb-3">
                <div className="col-4">
                  <label htmlFor="password" className="text-dark">
                    MẬT KHẨU MỚI
                  </label>
                </div>
                <div className="col-8">
                  <input
                    type="password"
                    id="password"
                    name="password"
                    value={data.password}
                    onChange={handleInputChange}
                    placeholder="Nhập mật khẩu mới"
                    style={{ width: "100%" }}
                    required
                    {...formik.getFieldProps("password")}
                  ></input>
                  {formik.touched.password && formik.errors.password && (
                    <div
                      className="error-message text-danger"
                      style={{ fontSize: 14 }}
                    >
                      {formik.errors.password}
                    </div>
                  )}
                </div>
              </div>
              <div className=" row mb-3">
                <div className="col-4">
                  <label htmlFor="confirmPassword" className="text-dark">
                    XÁC NHẬN MẬT KHẨU
                  </label>
                </div>
                <div className="col-8">
                  <input
                    type="password"
                    id="confirmPassword"
                    name="confirmPassword"
                    placeholder="Nhập xác nhận mật khẩu"
                    style={{ width: "100%" }}
                    required
                    {...formik.getFieldProps("confirmPassword")}
                  ></input>
                  {formik.touched.confirmPassword &&
                    formik.errors.confirmPassword && (
                      <div
                        className="error-message text-danger"
                        style={{ fontSize: 14 }}
                      >
                        {formik.errors.confirmPassword}
                      </div>
                    )}
                </div>
              </div>
              <div className=" row mb-3">
                <div className="col-4">
                  <label htmlFor="verificationCodes" className="text-dark">
                    MÃ XÁC THỰC
                  </label>
                </div>
                <div className="col-8">
                  <input
                    type="text"
                    id="verificationCodes"
                    name="verificationCodes"
                    placeholder="Nhập mã xác thực"
                    value={data.verificationCodes}
                    onChange={handleInputChange}
                    style={{ width: "100%" }}
                    required
                    {...formik.getFieldProps("verificationCodes")}
                  ></input>
                  {formik.touched.verificationCodes &&
                    formik.errors.verificationCodes && (
                      <div
                        className="error-message text-danger"
                        style={{ fontSize: 14 }}
                      >
                        {formik.errors.verificationCodes}
                      </div>
                    )}
                </div>
              </div>
              <Link
                to="/forget-password"
                className="forget-password"
                style={{
                  transform: "translateX(320%)",
                  fontSize: 14,
                  position: "absolute",
                }}
              >
                Chưa có mã xác thực?
              </Link>
              <div style={{ width: "auto", marginTop: 30 }}>
                <button
                  type="submit"
                  className="btn"
                  style={{ width: 200, justifyContent: "center" }}
                >
                  {pending ? "Đổi mật khẩu..." : "Đổi mật khẩu"}
                </button>
              </div>
            </form>
            <ToastContainer />
          </div>
        </div>
      </div>
    </div>
  );
}

export default ChangePassword;
