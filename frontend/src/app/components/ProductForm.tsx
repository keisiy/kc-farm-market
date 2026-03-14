"use client";

/** css */
import "./productForm.css";

/** 関数・型 */
import { useState } from "react";
import { createProduct } from "@/lib/api/product";
import { Product } from "@/types/product";
import { FieldError } from "@/types/error";
import { ApiErrorResponse } from "@/types/error";

type Props = {
    onCreated: (product: Product) => void; // プロパティ　関数
};

export default function ProductForm({ onCreated }: Props) { // Props型から、{onCreated}だけ取り出す
    const [name, setName] = useState("");
    const [price, setPrice] = useState(0);
    const [errors, setErrors] = useState<FieldError[]>([]);

    const handleSubmit = async () => {
        try {
            const created = await createProduct({ name, price });
            /** 成功時はエラーをリセット */
            setErrors([]);

            /** 親へProductが作られたことを通知 通知する手段として関数呼び出し*/
            onCreated(created);
        } catch (err: unknown) {
            if (err instanceof Error) {
                /** 想定外のエラー */
                setErrors([]);
            } else {
                const apiError = err as ApiErrorResponse;
                /** デバッグ用にapiError.errorsをコンソールに出す */
                console.log(apiError.errors);
                /** errorsがない場合は空配列にする */
                setErrors(apiError.errors ?? []);
            }
        }
    };

    /** エラー表示用関数 */
    /** 役割：指定された field に対応するエラーがあれば、その message を返す */
    const getErrorMessage = (field: string) => {
        return errors.find(e => e.field === field)?.message;
    };

    return (
        <div>
            <h2>商品登録</h2>

            <div className="form-row">
                <div className="form-field">
                    <input 
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        placeholder="商品名"
                        style={{
                            border: getErrorMessage("name") ? "2px solid red" : "1px solid #ccc",
                        }}
                    />
                    {getErrorMessage("name") && (
                        <p style={{ color: "red", fontSize: "0.9rem" }}>
                            {getErrorMessage("name")}</p>
                    )}
                </div>
            
                <div className="form-field">
                    <input 
                        type="number"
                        value={price}
                        onChange={(e) => setPrice(Number(e.target.value))}
                        placeholder="価格"
                        style={{
                            border: getErrorMessage("price") ? "2px solid red" : "1px solid #ccc",
                        }}
                    />
                    {getErrorMessage("price") && (
                        <p style={{ color: "red", fontSize: "0.9rem"}}>
                            {getErrorMessage("price")}</p>
                    )}
                </div> 
            </div>
            
            <button onClick={handleSubmit}>登録</button>

        </div>

    );
}