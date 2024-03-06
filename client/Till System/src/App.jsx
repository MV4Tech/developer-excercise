
import './App.css'
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from './pages/Home/Home'
import ProductModal from './pages/Home/Components/ProductModal'

const App = createBrowserRouter(
  createRoutesFromElements(
    <>
       
          <Route path="/">
            <Route index element={<Home />} />
            <Route path="/ProductModal" element={<ProductModal />} />
          </Route>

    </>
  )
);

export default App