"use client";

import { useEffect, useState } from "react";

type Product = {
  id: number;
  name: string;
  price: number;
};

export default function Home() {
  const [products, setProducts] = useState<Product[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetch("http://localhost:8080/api/products")
      .then((res) => {
        if (!res.ok) {
          throw new Error("APIエラー");
        }
        return res.json();
      })
      .then((data: Product[]) => {
        setProducts(data);
      })
      .catch((err) => {
        setError(err.message);
      });
  }, []);

  if (error) {
    return <p style={{ color: "red" }}>エラー: {error}</p>;
  }

  return (
    <main>
      <h1>商品一覧</h1>
      <ul>
        {products.map((p) => (
          <li key={p.id}>
            {p.name}：{p.price}円
          </li>
        ))}
      </ul>
    </main>
  );
}
