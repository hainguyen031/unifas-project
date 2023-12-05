import React from "react";
import LoginForm from "../components/main/account/LoginForm";
import Subscribe from "../components/main/account/Subscribe";

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
