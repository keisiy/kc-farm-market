"use client";

import { useState } from "react";
import { createProduct } from "@/lib/api/product";

export default function ProductForm() {
    const [name, setName] = useState("");
    const [price, setPrice] = useState(0);

    const handleSubmit = async () => {
        await createProduct({ name, price });
        alert("登録完了");
    };

    return (
        <div>
            <h2>商品登録</h2>

            <input 
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="商品名"
            />
                
            <input 
                type="number"
                value={price}
                onChange={(e) => setPrice(Number(e.target.value))}
                placeholder="価格"
            />

            <button onClick={handleSubmit}>登録</button>
        </div>
    );
}