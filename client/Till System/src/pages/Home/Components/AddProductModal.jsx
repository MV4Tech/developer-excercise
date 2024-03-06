import React from "react";
import { useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import ProductService from "../../../services/product-service";





const AddProductModal = () => {

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [product, setProduct] = useState({
        name: "",
        cloud: 0
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setProduct((prevProduct) => ({
            ...prevProduct,
            [name]: value
        }));
    };



    const saveProduct = async(e) => {
        try{
            await ProductService.saveProduct(product);
            window.location.reload();
            handleClose();
        }catch(e){
            console.log(e);
        }
    }

  return (
    <>
      <div className='mb-3 d-flex justify-content-end'>
            <Button onClick={handleShow} variant="primary">Add Product</Button>
            </div>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add Product</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <Form >
        <Form.Group className="mb-3" >
            <Form.Label>Product Name</Form.Label>
            <Form.Control name="name"
             value={product.name}
             onChange={handleInputChange}
              type="text"
             
              />
        </Form.Group>
        <Form.Group className="mb-3">
            <Form.Label>Recommended Price in clouds</Form.Label>
            <Form.Control name="cloud"
            value={product.cloud}
            onChange={handleInputChange}
              type="number"
               placeholder="Enter price"
              
                />
        </Form.Group>
        </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button onClick={saveProduct}  variant="primary">
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default AddProductModal;
