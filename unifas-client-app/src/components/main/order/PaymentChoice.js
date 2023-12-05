import React, {useEffect} from 'react';
import {BsCash} from "react-icons/bs";
import $ from 'jquery';

const PaymentChoice = () => {
    useEffect(() => {
            $('.payment-method-choice').on('click', function () {
                let input = $(this).find('input');
                input.prop('checked', true);
            })
        }
    )

    return (
        <div>
            <div className="card mt-4" style={{padding: "20px"}}>
                <h4>Payment method</h4>
                <div className="col payment-method-choice card pt-30 pb-30" style={{padding: "30px"}}>
                    <div className="row d-flex align-items-center">
                        <div className="col-2 d-flex align-items-center pl-50 icon-payment-1">
                            <input type="radio" name="payment" value="cod" checked/>
                            <span className="pr-4 pl-4"><BsCash style={{fontSize: "40px", color: "green"}}/></span>

                        </div>
                        <div className="col-8 d-flex justify-content-center choice">
                                        <span style={{textAlign: "center"}}>
                                        COD
                                            <br/>
                                        Pay cash upon receipt
                                    </span>
                        </div>
                    </div>
                </div>

                <div className="col payment-method-choice card pt-30 pb-30 mt-4" style={{padding: "30px"}}>
                    <div className="row d-flex align-items-center">
                        <div className="col-2 d-flex align-items-center pl-50 icon-payment-2">
                            <input type="radio" name="payment" value="vnpay"/>
                            <span className="pr-4 pl-4"><img className="img-fluid" src="/img/logo/logo-vnpay.webp"
                                                             alt="vnpay-logo" style={{scale: "4", marginLeft: "20px"}}/></span>

                        </div>
                        <div className="col-8 d-flex justify-content-center choice">
                                        <span style={{textAlign: "center", fontWeight: "500"}}>
                                        COD
                                            <br/>
                                        Pay cash upon receipt
                                    </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default PaymentChoice;