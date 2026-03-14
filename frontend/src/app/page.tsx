"use client"

import { useEffect, useState } from "react";
import { fetchProducts } from "@/lib/api/product";
import ProductForm from "./components/ProductForm";
import {Product} from "@/types/product";

export default function Page() {
  const [products, setProducts] = useState<Product[]>([]);
  

  useEffect(() => {
    fetchProducts().then(setProducts);
  }, []);

  return (
    <>
      <ProductForm
        onCreated={(product) => 
          setProducts(prev => [...prev, product])
        }
      />

      <ul>
      {products.map(p => (
        <li key={p.id}>
          {p.name} - ¥{p.price}
        </li>
      ))}
    </ul>
    </>
  );
}
