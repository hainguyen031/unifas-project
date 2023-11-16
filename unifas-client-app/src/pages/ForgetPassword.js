import { ErrorMessage, Formik } from "formik";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import * as Yup from "yup";
import {
  forgotPasswordUser,
  selectError,
  selectLoading,
  selectSuccess,
  selectUserCodePass,
  setLoading,
} from "../feature/userSlice";

function ForgetPassword() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const userCode = useSelector(selectUserCodePass);
  const success = useSelector(selectSuccess);
  const pending = useSelector(selectLoading);
  const error = useSelector(selectError);

  const validationSchema = Yup.object().shape({
    email: Yup.string()
      .email("Vui lòng nhập một địa chỉ email hợp lệ.")
      .required("Vui lòng nhập một địa chỉ email.")
      .matches(
        /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/,
        "Vui lòng nhập một địa chỉ email hợp lệ."
      ),
  });

  const [emailData, setEmailData] = useState({
    email: "",
  });

  useEffect(() => {
    if (success) {
      localStorage.setItem("codePass", userCode.codePass);
      toast.success("Gửi email thành công !", {
        position: toast.POSITION.TOP_RIGHT,
        type: toast.TYPE.SUCCESS,
      });
      dispatch(setLoading(false));
      setTimeout(() => {
        navigate("/change-password");
      }, 3000);
    } else if (error) {
      toast.error(
        "Gửi email không thành công, hãy kiểm tra lại địa chỉ email của bạn !",
        {
          position: toast.POSITION.TOP_RIGHT,
          type: toast.TYPE.ERROR,
        }
      );
    }
  }, [pending]);

  const handleInputChange = (e) => {
    setEmailData({ ...emailData, [e.target.name]: e.target.value });
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    dispatch(forgotPasswordUser(emailData));
    toast.success("Đang gửi email ... !", {
      position: toast.POSITION.TOP_RIGHT,
      type: toast.TYPE.DEFAULT,
    });
  };

  return (
    <Formik
      initialValues={{
        email: "",
      }}
      validationSchema={validationSchema}
    >
      {({ errors, touched }) => (
        <main>
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
                      marginBottom: 30,
                    }}
                  >
                    <div>Vui lòng nhập địa chỉ email bạn đã đăng ký</div>
                    <div
                      className="text-info"
                      style={{ position: "absolute", right: 0 }}
                    >
                      Bắt buộc*
                    </div>
                  </div>
                  <form onSubmit={handleFormSubmit}>
                    <div className="form-grp row">
                      <div className="col-3">
                        <label htmlFor="email" className="text-dark">
                          ĐỊA CHỈ EMAIL <span>*</span>
                        </label>
                      </div>
                      <div className="col-9">
                        <input
                          type="text"
                          id="email"
                          name="email"
                          value={emailData.to}
                          onChange={handleInputChange}
                          placeholder="Nhập email hợp lệ"
                          style={{ width: "100%" }}
                          required
                          className={
                            errors.email && touched.email ? "input-error" : ""
                          }
                        />
                        <ErrorMessage
                          name="email"
                          component="div"
                          className="error"
                          style={{ color: "red", fontSize: "12px" }}
                        />
                        {errors.email && touched.email && (
                          <div
                            className="error-message"
                            style={{ color: "red", fontSize: "12px" }}
                          ></div>
                        )}
                      </div>
                    </div>
                    <div style={{ width: "auto", marginTop: 30 }}>
                      <button
                        type="submit"
                        className="btn"
                        style={{ width: 200, justifyContent: "center" }}
                      >
                        {pending ? "Gửi..." : "Gửi"}
                      </button>
                    </div>
                  </form>
                  <ToastContainer />
                </div>
              </div>
            </div>
          </div>
        </main>
      )}
    </Formik>
  );
}

export default ForgetPassword;
