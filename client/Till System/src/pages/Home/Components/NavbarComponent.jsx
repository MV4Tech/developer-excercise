import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";
import { Dropdown } from "react-bootstrap";
import { Link } from "react-router-dom";
import { NavLink } from "react-router-dom";
import ProductModal from "./ProductModal";

const NavbarComponent = () => {
  return (
    <div>
      <div
        className="bg-white d-flex align-items-center fixed-top shadow"
        style={{ minHeight: "56px", zIndex: "5" }}
      >
        <div className="container-fluid ">
          <h2 className="text-center" style={{ marginLeft: "100px" }}>
            Till System
          </h2>
        </div>
        <div className="justify-items-end" style={{ marginRight: "60px" }}>
          <Dropdown className="custom-dropdown">
            <Dropdown.Toggle variant="success" id="dropdown-basic">
              <FontAwesomeIcon icon={faBars} size="lg" />
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <ProductModal />
            </Dropdown.Menu>
          </Dropdown>
        </div>
      </div>
    </div>
  );
};

export default NavbarComponent;
