import React from "react";
import RegisterForm from "../components/main/account/RegisterForm";
import Subscribe from "../components/main/account/Subscribe";

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
