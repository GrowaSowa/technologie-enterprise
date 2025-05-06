import './App.css';
import React from 'react';
import ReactDOM from 'react-dom/client';
import ProductList from './ProductList';
import ProductDetails from './ProductDetails';
import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

function App() {
  var [products, setProducts] = useState([]);
  useEffect(() => {
		fetch("https://dummyjson.com/products")
			.then((response) => response.json())
			.then((json) => setProducts(json.products));
	}, []);

  const router = createBrowserRouter([
    {
      path: "/",
      element: <ProductList products={products}/>
    },
    {
      path: "details/:id",
      element: <ProductDetails products={products}/>
    }
  ]);

  ReactDOM.createRoot(document.getElementById("root")).render(
    <div className="App">
      <React.StrictMode>
        <RouterProvider router={router}/>
      </React.StrictMode>
    </div>
  );
}

export default App;
