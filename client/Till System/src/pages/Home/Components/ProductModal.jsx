import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Dropdown from 'react-bootstrap/Dropdown';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import ProductTableComponent from './ProductTableComponent';

function ProductModal() {
  const [lgShow, setLgShow] = useState(false);

  const handleShow = () => setLgShow(true);
  const handleClose = () => setLgShow(false);

  return (
    <>
      <Dropdown.Item onClick={handleShow}>Products</Dropdown.Item>
     
      <Modal
        size="lg"
        show={lgShow}
        onHide={handleClose}
        aria-labelledby="example-modal-sizes-title-lg"
      >
        <Modal.Header closeButton>
          <Modal.Title id="example-modal-sizes-title-lg">
           Products supported by the system
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
         <ProductTableComponent />   
        </Modal.Body>
      </Modal>
    </>
  );
}

export default ProductModal;
