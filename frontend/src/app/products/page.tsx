"use client";

import ProductForm from "@/app/components/ProductForm";
import { Product } from "@/types/product";
import { useEffect, useState } from "react";
import { fetchProducts } from "@/lib/api/product";

export default function ProductRegisterPage() {
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        fetchProducts().then(setProducts);
    }, []);

    return (
        <>
            {/** onCreated はProductForm定義時に指定した関数 */}
            <ProductForm onCreated={(product) => 
                setProducts(prev => [...prev, product])
            } />

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