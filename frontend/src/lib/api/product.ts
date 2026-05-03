import { apiFetch } from "./client";
import { Product } from "@/types/product";

type ProductCreateRequest = {
    name: string,
    price: number
};

/** POST用API */
export async function createProduct(
    request: ProductCreateRequest
): Promise<Product> {
    

    return apiFetch<Product>("/api/products", {
        method: "POST",
        body: JSON.stringify(request),
    });
}

/** GET用API */
export function fetchProducts(): Promise<Product[]> {
    return apiFetch<Product[]>("/api/products");
}