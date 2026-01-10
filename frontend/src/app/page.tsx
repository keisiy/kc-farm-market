
import { fetchProducts } from "@/lib/api/product";
import ProductForm from "./ProductForm";

export default async function Page() {
  const products = await fetchProducts();

  return (
    <>
      <ProductForm />

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
