import { apiFetch } from "./client";
import { Product } from "@/types/product";

export function fetchProducts(): Promise<Product[]> {
    return apiFetch<Product[]>("/products");
}