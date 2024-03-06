import React from "react";
import NavbarComponent from "./Components/NavbarComponent";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTag } from "@fortawesome/free-solid-svg-icons";
import "./styles/home.css";
import ScanNoDiscountComponentModal from "./Components/ScanNoDiscountComponentModal";
import Scan3for2DiscountComponentModal from "./Components/Scan3for2DiscountComponentModal";
import ScanPaidOneSecondHalfPriceComponentModal from "./Components/ScanPaidOneSecondHalfPriceComponentModal";
import ScanWithAllDiscounts from "./Components/ScanWithAllDiscounts";

const Home = () => {
  return (
    <>
      <div style={{ marginBottom: "90px" }}>
        <NavbarComponent />
      </div>

      <div className="container-fluid">
        <div className="row">
          <div className="col-md-6">
            {/* Content for the left column */}
            <div
              className="mt-3 d-flex flex-column align-items-center justify-content-center"
              style={{ minHeight: "200px", zIndex: "5" }}
            >
              <h3 className="text-center">No Discount</h3>
              <div>
                <ScanNoDiscountComponentModal />
              </div>
            </div>
          </div>
          <div className="col-md-6">
            {/* Content for the right column */}
            <div
              className="mt-5 d-flex flex-column align-items-center justify-content-center"
              style={{ minHeight: "200px", zIndex: "5" }}
            >
              <div className="d-flex align-items-center">
                <h3 className="text-center">Discounts</h3>
                <FontAwesomeIcon
                  className="p-2"
                  icon={faTag}
                  style={{ color: "#ff0000" }}
                />
              </div>

              <div className="mt-3">
               <Scan3for2DiscountComponentModal />
              </div>
              <div className="mt-3">
                <ScanPaidOneSecondHalfPriceComponentModal />
              </div>
              <div className="mt-3">
                <ScanWithAllDiscounts />
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;
