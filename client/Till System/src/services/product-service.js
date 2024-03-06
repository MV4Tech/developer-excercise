import axios from 'axios';
import { SERVER_URL } from '../shared/constants';

class ProductService{
    async saveProduct(product){
       let response = await axios.post(SERVER_URL + "api/v1/product/add-product", product);
    }

    getProducts(){
        return axios.get(SERVER_URL + "api/v1/product/fetch-all-products");
    }

    async deleteProduct(id){
        let response = await axios.delete(SERVER_URL + "api/v1/product/delete-product/" + id);
    }

    async scanWithoutDiscount(products){
        return axios.post(SERVER_URL + "api/v1/scanner/scan-without-discount", products);
    }

    async scan3for2discount(products){
        return axios.post(SERVER_URL + "api/v1/scanner/scan-with-3for2-discount", products);
    }

    async scanPaidOneSecondHalfPrice(products){
        return axios.post(SERVER_URL + "api/v1/scanner/scan-get-1-second-half-price", products);
    }

    async scanWithAllDiscounts(products){
        return axios.post(SERVER_URL + "api/v1/scanner/scan-with-all-discount", products);
    }
}

export default new ProductService();