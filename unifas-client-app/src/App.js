import "./App.css";
import Header from "./components/common/Header";
import Footer from "./components/common/Footer";
import React, { useEffect, useState } from "react";
import OrderPage from "./pages/OrderPage";
import Loading from "./components/common/Loading";
import { Route, Routes } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import CartPage from "./pages/CartPage";
import ProductPage from "./pages/ProductPage";
import ForgetPassword from "./pages/ForgetPassword";
import ChangePassword from "./pages/ChangePassword";
import DashBoard from "./components/main/account/DashBoard";
import List from "./pages/list/List";
import AdminProduct from "./pages/adminSide/AdminProduct";
import UserDetailPage from "./pages/UserDetailPage";



function App() {
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
    }, 3000);
  }, []);

  return (
    <div className="App">
  
      {loading ? (
        <Loading />
      ) : (
        <>
          <Header />
          <Routes>
            <Route path="/" element={<ProductPage />}></Route>
            <Route path="/login" element={<LoginPage />}></Route>
            <Route path="/register" element={<RegisterPage />}></Route>
            <Route path="/cart" element={<CartPage />}></Route>
            <Route path="/forget-password" element={<ForgetPassword />}></Route>
            <Route path="/change-password" element={<ChangePassword />}></Route>
            <Route path="/order" element={<OrderPage />}></Route>
            <Route path="/dashboard" element={<DashBoard />}></Route>
            <Route path="/users" element={<List />}></Route>
            <Route path="/products" element={<AdminProduct />}></Route>
            <Route path="/user-detail" element={<UserDetailPage />}></Route>
          </Routes>
          <Footer />
        </>
      )}
    </div>
  );
}
export default App;
