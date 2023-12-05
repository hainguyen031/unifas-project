import React from 'react';
import "../../css/common/loading.css"
const Loading = () => {

    return (
        <div>
            <div className="background d-flex justify-content-center align-items-center">
                <div className="logo d-flex justify-content-center align-items-center mb-3">
                <img src="/img/logo/UNIFAS-200px.png" alt="" style={{maxWidth : "50%"}}/>
                </div>
            <div className="wrapper">
                <div className="circle"></div>
                <div className="circle"></div>
                <div className="circle"></div>
                <div className="shadow"></div>
                <div className="shadow"></div>
                <div className="shadow"></div>
            </div>
            </div>
        </div>
    );
};

export default Loading;