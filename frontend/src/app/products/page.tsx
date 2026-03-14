"use client";

import ProductForm from "@/app/components/ProductForm";
import { Product } from "@/types/product";

export default function ProductRegisterPage() {
    const hundleCreated = (product: Product) => {
        console.log("親が受け取ったProduct：", product);
    }

    return (
        <>
            {/** onCreated はProductForm定義時に指定した関数 */}
            <ProductForm onCreated={hundleCreated} />
        </>
    );
}