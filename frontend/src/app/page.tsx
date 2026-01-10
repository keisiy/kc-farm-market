
import { fetchProducts } from "@/lib/api/product";

export default async function Page() {
  const products = await fetchProducts();

  return (
    <ul>
      {products.map(p => (
        <li key={p.id}>
          {p.name} - ¥{p.price}
        </li>
      ))}
    </ul>
  );
}
