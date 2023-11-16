import React from "react";
import LoginForm from "../components/account/LoginForm";
import Subscribe from "../components/account/Subscribe";

function LoginPage() {
  return (
    <main>
      <Subscribe />
      <LoginForm />
      {/*<NewsLetter/>*/}
    </main>
  );
}

export default LoginPage;
