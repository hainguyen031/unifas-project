import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function LoginForm() {

  const navigate = useNavigate();

  const validationSchema = Yup.object().shape({
    email: Yup.string()
      .email("Invalid email address")
      .required("Email is required"),
    password: Yup.string()
      .required("Password is required")
      .min(8, "Password must be at least 8 characters")
      .matches(
        /^(?=.*[A-Za-z])(?=.*\d).*$/,
        "Password must contain both letters and numbers"
      ),
  });

  const onSubmitHandler = async (values) => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/login",
        values
      );

      toast.success("Login successful!", {
        position: "top-right",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
      });

      localStorage.setItem("username", response.data.username);
      
      localStorage.setItem("token", response.data.token);

      await new Promise((resolve) => setTimeout(resolve, 2000));

      navigate("/");
      
    } catch (error) {
      console.error("API error:", error);

      toast.error("Login failed. Please check your credentials.", {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
      });
    }
  };

  return (
    <>
      <ToastContainer />
      <Formik
        initialValues={{
          email: "",
          password: "",
        }}
        validationSchema={validationSchema}
        onSubmit={onSubmitHandler}
      >
        {({ errors, touched }) => (
          <section className="contact-area pt-110 pb-110">
            <div className="container">
              <div className="container-inner-wrap">
                <div className="row justify-content-center justify-content-lg-between">
                  <div className="col-lg-6 col-md-6 mx-auto">
                    <div className="mb-220 d-flex justify-content-center">
                      <h1 className="title" style={{ color: "#f04336" }}>
                        <Link to="/login">Login</Link>
                      </h1>
                      <h4 className="title">
                        <Link to="/register">Register</Link>
                      </h4>
                    </div>
                    <div className="contact-wrap-content">
                      <Form className="contact-form">
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
                        </div>
                        
                        <div className="form-grp checkbox-grp">
                          <div className="remember-me">
                            <input type="checkbox" id="checkbox" />
                            <label htmlFor="checkbox">Remember Me</label>
                          </div>
                          <Link
                            to="/forget-password"
                            className="forget-password"
                            style={{ marginLeft: "220px" }}
                          >
                            Forget Password?
                          </Link>
                        </div>

                        <button type="submit" className="btn rounded-btn">
                          LOGIN
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

export default LoginForm;