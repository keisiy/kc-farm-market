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
    const response = await fetch("http://localhost:8080/api/products", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
    });

    if(!response.ok) {
        const errorJson = await response.json();
        throw errorJson;
    } 

    return response.json();
}

/** GET用API */
export function fetchProducts(): Promise<Product[]> {
    return apiFetch<Product[]>("/products");
}