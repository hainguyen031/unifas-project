import React from "react";
import { BiUser } from "react-icons/bi";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { SlUserFollowing } from "react-icons/sl";
import { VscAccount } from "react-icons/vsc";
import { VscTable } from "react-icons/vsc";
import { VscRecord } from "react-icons/vsc";
import axios from "axios";

function BeforeAfterLogin() {
  const navigate = useNavigate();

  const storedUsername = localStorage.getItem("username");
  const role = localStorage.getItem("role");
  const token = localStorage.getItem("token");
  const [username, setUsername] = useState(storedUsername);

  useEffect(() => {
    if (storedUsername) {
      setUsername(storedUsername);
      navigate("/");
    }
  }, [storedUsername]);

  const handleLogout = async() => {

    // try {
    //     const cartItemsFromLocalStorage = localStorage.getItem("cartItems");

    //     if (cartItemsFromLocalStorage) {
          
    //       const cartItems = JSON.parse(cartItemsFromLocalStorage);
    //       const requestData = { cartItems };

    //       const response = await axios.post(
    //         "http://localhost:8080/api/cart",
    //         requestData,
    //         {
    //           headers: {
    //             "Content-Type": "application/json",
    //             Authorization: `Bearer ${token}`,
    //           },
    //         }
    //       );

    //       if (response.status === 200) {
    //         } else {
    //           console.error(
    //             "Failed to save information to the database. Server returned:",
    //             response
    //           );
    //         }
    //       } else {
    //         console.error("No cart items found in Local Storage.");
    //       }
    //     } catch (error) {
    //       console.error(
    //         "Error sending request to save information to the database:",
    //         error
    //       );
    //     }
    localStorage.removeItem("username");
    setUsername(null);
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    localStorage.removeItem("id");
    navigate("/login");
  };

  return (
    <div>
      {username !== null ? (
        <li className="header-shop-cart">
          <Link to="/">
            <BiUser style={{ fontSize: "27px", color: "#525252" }}></BiUser>
          </Link>

          <ul className="minicart">
            <li className="d-flex align-items-start">
             
                <Link to="/#">
                  <VscAccount
                    style={{
                      fontSize: "20px",
                      color: "red",
                      marginRight: "10px",
                    }}
                  ></VscAccount>
                </Link>

                <Link to="/" style={{ fontSize: "16px", color: "black" }}>
                  {username}
                </Link>
            
            </li>
            <li className="d-flex align-items-start">
              <a href="/#">
                <SlUserFollowing
                  style={{
                    fontSize: "20px",
                    color: "red",
                    marginRight: "10px",
                  }}
                ></SlUserFollowing>
              </a>

              <Link to="/user-detail" style={{ fontSize: "16px", color: "black" }}>
                Account Profile
              </Link>
            </li>

            {role === "ROLE_ADMIN" && (
              <li className="d-flex align-items-start">
                 <Link to="/dashboard">
                  <VscTable
                    style={{
                      fontSize: "20px",
                      color: "red",
                      marginRight: "10px",
                    }}
                  ></VscTable>
                </Link>

                <Link to="/dashboard" style={{ fontSize: "16px", color: "black" }}>
                  Dashboard
                </Link>
              </li>
            )}

            <li className="d-flex align-items-start">
              <a href="/#" onClick={handleLogout}>
                <VscRecord
                  style={{
                    fontSize: "20px",
                    color: "red",
                    marginRight: "10px", 
                  }}
                ></VscRecord>
              </a>

              <Link
                to="/#"
                onClick={handleLogout}
                style={{ fontSize: "16px", color: "black" }}
              >
                Logout
              </Link>
            </li>
          </ul>
        </li>
      ) : (
        <li>
          <Link to="/login">
            <BiUser style={{ fontSize: "27px", color: "#525252" }}></BiUser>
          </Link>
        </li>
      )}
    </div>
  );
}

export default BeforeAfterLogin;
