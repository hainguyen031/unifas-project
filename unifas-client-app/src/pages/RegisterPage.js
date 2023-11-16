import React from "react";
import RegisterForm from "../components/account/RegisterForm";
import Subscribe from "../components/account/Subscribe";

function RegisterPage() {
  return (
    <main>
      <Subscribe />
      <RegisterForm />
      {/*<NewsLetter/>*/}
    </main>
  );
}

export default RegisterPage;
