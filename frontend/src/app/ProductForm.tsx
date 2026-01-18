"use client";

import { useState } from "react";
import { createProduct } from "@/lib/api/product";
import { Product } from "@/types/product";
import { FieldError } from "@/types/error";
import { ApiErrorResponse } from "@/types/error";

type Props = {
    onCreated: (product: Product) => void;
};

export default function ProductForm({ onCreated }: Props) {
    const [name, setName] = useState("");
    const [price, setPrice] = useState(0);
    const [errors, setErrors] = useState<FieldError[]>([]);

    const handleSubmit = async () => {
        try {
            const created = await createProduct({ name, price });
            /** 成功時はエラーをリセット */
            setErrors([]);

            /** 親へ通知 */
            onCreated(created);
        } catch (err: unknown) {
            if (err instanceof Error) {
                /** 想定外のエラー */
                setErrors([]);
            } else {
                const apiError = err as ApiErrorResponse;
                /** errorsがない場合は空配列にする */
                setErrors(apiError.errors ?? []);
            }
        }
    };

    /** エラー表示用関数 */
    const getErrorMessage = (field: string) => {
        return errors.find(e => e.field === field)?.message;
    };

    return (
        <div>
            <h2>商品登録</h2>

            <input 
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="商品名"
            />
            {getErrorMessage("name") && (
                <p style={{ color: "red"}}>{getErrorMessage("name")}</p>
            )}
                
            <input 
                type="number"
                value={price}
                onChange={(e) => setPrice(Number(e.target.value))}
                placeholder="価格"
            />
            {getErrorMessage("price") && (
                <p style={{ color: "red"}}>{getErrorMessage("price")}</p>
            )}

            <button onClick={handleSubmit}>登録</button>

        </div>

    );
}