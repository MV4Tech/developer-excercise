import React from 'react'
import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import ProductService from '../../../services/product-service';
import '../styles/scan.css'


const Scan3for2DiscountComponentModal = () => {
  
    const [lgShow, setLgShow] = useState(false);
    const [showPriceModal, setShowPriceModal] = useState(false);
    const [products, setProducts] = useState([]);
    const [productName, setProductName] = useState('');
    const [productCloud, setProductCloud] = useState('');
    const [totalPrice, setTotalPrice] = useState(0);
  
    const handleInputChange = (e) => {
      const { name, value } = e.target;
      if (name === 'name') {
        setProductName(value);
      } else if (name === 'cloud') {
        setProductCloud(value);
      }
      setInputErrors({})
    };
  
    const addProduct = () => {
      if (productName.trim() !== '' && productCloud.trim() !== '') {
        setProducts([...products, { name: productName, cloud: parseInt(productCloud) }]);
        setProductName('');
        setProductCloud('');
      }
    };
  

    const [inputErrors, setInputErrors] = useState();

    const handleSubmit = async () => {
      try {
        const response = await ProductService.scan3for2discount(products);
        setTotalPrice(response.data);
        setShowPriceModal(true);
      } catch (error) {
        const errorMessage = error.response.data.errors[0];
            setInputErrors({ errorMessage });
            console.log("tyk sym");
            setProducts([]);
            setProductName('');
            setProductCloud('');
      }
    };

    

    const handleClose = () => {
        setLgShow(false);
        setProducts([]);
        setProductName('');
        setProductCloud('');
        setInputErrors({});
    }
  
    const handleClosePriceModal = () => {
      setShowPriceModal(false);
      setProducts([]);
      setProductName('');
      setProductCloud('');
    };

  return (
    <div>
               
       {/*<button onClick={() => setLgShow(true)} className="custom-button">Scan</button>*/}
       <button onClick={() => setLgShow(true)} className="custom-button-3-for-2" >3 for 2</button>
      
     <Modal
      size='lg'
      show={lgShow}
      onHide={handleClose}
      aria-labelledby='example-modal-sizes-title-lg'
    >
      <Modal.Header closeButton>
        <Modal.Title id='example-modal-sizes-title-lg'>Scan without discount</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className='mb-3'>
            <Form.Label>Product Name</Form.Label>
            <Form.Control
              name='name'
              value={productName}
              onChange={handleInputChange}
              type='text'
              placeholder='Enter product name'
            />
          </Form.Group>
          <Form.Group className='mb-3'>
            <Form.Label>Cloud (Price)</Form.Label>
            <Form.Control
              name='cloud'
              value={productCloud}
              onChange={handleInputChange}
              type='number'
              placeholder='Enter price'
            />
            <span className="error-message"><br/>{inputErrors && inputErrors.errorMessage}</span>
          </Form.Group>
          
          <Button onClick={addProduct} variant='primary'>
            Add
          </Button>
        </Form>
        <div className='mt-3'>
          <h5>Current Products:</h5>
          <ul>
            {products.map((product, index) => (
              <li key={index}>{`${product.name}: ${product.cloud} clouds`}</li>
            ))}
          </ul>
        </div>
      </Modal.Body>
      <Modal.Footer>
        <Button variant='secondary' onClick={handleClose}>
          Close
        </Button>
        <Button onClick={handleSubmit} variant='primary'>
          Paid
        </Button>
      </Modal.Footer>
    </Modal>

    <Modal show={showPriceModal} onHide={handleClosePriceModal}>
      <Modal.Header closeButton>
        <Modal.Title>Final Price With 3 for 2 Discount</Modal.Title>
      </Modal.Header>
      <Modal.Body>
      <p className='mt-3'><strong>Final Price:</strong> {totalPrice}</p>
      </Modal.Body>
      <Modal.Footer>
        <Button variant='secondary' onClick={handleClosePriceModal}>
          Close
        </Button>
      </Modal.Footer>
    </Modal>
    </div>
  )
}

export default Scan3for2DiscountComponentModal