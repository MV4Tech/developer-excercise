import React, { useEffect } from 'react'
import { useState } from 'react'
import { Button, Table } from 'react-bootstrap'
import '../styles/table.css'
import AddProductModal from './AddProductModal'
import ProductService from '../../../services/product-service'




const ProductTableComponent = () => {

    const removeProduct = (id) => {
      const updatedProducts = products.filter(product => product.id !== id);
      try{
        ProductService.deleteProduct(id);
        }catch(e){
            console.log(e);
        }
      setProducts(updatedProducts);
    };

  
   

    const [loading, setLoading] = useState(true);
    const[products, setProducts] = useState(false)
    useEffect(() => {

        const fetchProducts = async () => {
            setLoading(true);
            try{
              const response = await ProductService.getProducts();
              setProducts(response.data);
            }catch(e){
                console.log(e);
            }
            setLoading(false);
        };
        fetchProducts();
    },[]);

    return (
        <div className="product-table">

         <AddProductModal />   

        <Table striped bordered hover>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Recommended Cloud (Price)</th>
              <th>Function</th>
            </tr>
          </thead>
          {!loading &&(
          <tbody>
                {products.map((product) => (
              <tr key={product.id}>
                <td>{product.id}</td>
                <td>{product.name}</td>
                <td>{product.cloud}</td>
                <td>
                  <Button variant="danger" onClick={() => removeProduct(product.id)}>Remove</Button>
                </td>
              </tr>))}
          </tbody>)}
        </Table>
      </div>
    );
}

export default ProductTableComponent