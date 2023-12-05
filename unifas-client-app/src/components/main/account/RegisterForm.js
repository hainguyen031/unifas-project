import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { Formik, Form, Field, ErrorMessage} from "formik";
import * as Yup from "yup";
import { ToastContainer, toast } from "react-toastify";
import axios from "axios";

function RegisterForm() {
  const navigate = useNavigate();
  const validationSchema = Yup.object().shape({
    username: Yup.string()
      .required("Username is required")
      .matches(
        /^[a-z0-9_-]{8,20}$/,
        "Username can only use letters, numbers, minimum length is 8 characters"
      ),
    email: Yup.string()
      .email("Invalid email")
      .required("Email is required")
      .matches(/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/, "Invalid email"),
    password: Yup.string()
      .required("Password is required")
      .matches(
        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()])(?=.*[A-Z])[A-Za-z\d!@#$%^&*()]{8,}$/,
            "Password must contain at least 8 characters, one number, one special character, and one capital letter"
      ),
    confirmPassword: Yup.string()
      .required("Confirm password is required")
      .oneOf([Yup.ref("password"), null], "Password must match"),
  });

  const submitHandler = (values)=> {
    const {username,email,password} = values;
    console.log(username);
    axios.post("http://localhost:8080/api/auth/register",{username,email,password})
    .then((res)=>{
      console.log(res);
      if(res.data.data ==false){
        toast.error(res.data.message);
      }else if(res.data.data){
        toast.success(res.data.message);
        navigate("/login");
      }
    })
    .catch(e => console.log("error ",e))
  }

  return (
    <>
    <ToastContainer 
    autoClose={1500}
    hideProgressBar={false}
    newestOnTop={false}
    closeOnClick
    rtl={false}
    pauseOnFocusLoss
    draggable
    pauseOnHover
    theme="dark"
    />
    <Formik
      initialValues={{
        username: "",
        email: "",
        password: "",
        confirmPassword: "",
      }}
      validationSchema={validationSchema}
      onSubmit={submitHandler}
    >
      
      {({ errors, touched }) => (
        <section className="contact-area pt-110 pb-110">
          <div className="container">
            <div className="container-inner-wrap">
              <div className="row justify-content-center justify-content-lg-between">
                <div className="col-lg-6 col-md-6 mx-auto">
                  <div className=" mb-20 d-flex justify-content-center">
                    <h4 className="title">
                      <Link to="/login">
                        Login<span></span>
                      </Link>
                    </h4>
                    <h1 className="title">
                      <Link to="/register" style={{ color: "#f04336" }}>
                        Register<span></span>
                      </Link>
                    </h1>
                  </div>

                  <div className="contact-wrap-content">
                    <Form className="contact-form">
                      <div className="form-grp">
                        <label htmlFor="email">
                          Username <span>*</span>
                        </label>
                        <Field
                          type="text"
                          id="username"
                          name="username"
                          placeholder="Username"
                          className={
                            errors.username && touched.username
                              ? "input-error"
                              : ""
                          }
                        />
                        <ErrorMessage
                          name="username"
                          component="div"
                          className="error"
                          style={{ color: "red", fontSize: "12px" }}
                        />
                        {errors.username && touched.username && (
                          <div
                            className="error-message"
                            style={{ color: "red", fontSize: "12px" }}
                          ></div>
                        )}
                      </div>
                      <div className="form-grp">
                        <label htmlFor="email">
                          Email <span>*</span>
                        </label>
                        <Field
                          type="text"
                          id="email"
                          name="email"
                          placeholder="info.example@.com"
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
                      <div className="form-grp">
                        <label htmlFor="password">
                          Password <span>*</span>
                        </label>
                        <Field
                          type="password"
                          id="password"
                          name="password"
                          placeholder="********"
                          className={
                            errors.password && touched.password
                              ? "input-error"
                              : ""
                          }
                        />
                        <ErrorMessage
                          name="password"
                          component="div"
                          className="error"
                          style={{ color: "red", fontSize: "12px" }}
                        />
                        {errors.password && touched.password && (
                          <div
                            className="error-message"
                            style={{ color: "red", fontSize: "12px" }}
                          ></div>
                        )}
                      </div>
                      <div className="form-grp">
                        <label htmlFor="confirmPassword">
                          Confirm Password <span>*</span>
                        </label>
                        <Field
                          type="password"
                          id="confirmPassword"
                          name="confirmPassword"
                          placeholder="********"
                          className={
                            errors.confirmPassword && touched.confirmPassword
                              ? "input-error"
                              : ""
                          }
                        />
                        <ErrorMessage
                          name="confirmPassword"
                          component="div"
                          className="error"
                          style={{ color: "red", fontSize: "12px" }}
                        />
                        {errors.confirmPassword && touched.confirmPassword && (
                          <div
                            className="error-message"
                            style={{ color: "red", fontSize: "12px" }}
                          ></div>
                        )}
                      </div>

                      <button type="submit" className="btn rounded-btn">
                        Register
                      </button>
                    </Form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      )}
    </Formik>
    </>
  );
}

export default RegisterForm;
