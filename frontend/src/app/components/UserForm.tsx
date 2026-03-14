"use client";

import { useState } from "react";
import { createUser } from "@/lib/api/user";
import { User } from "@/types/user";
import { FieldError } from "@/types/error";
import { ApiErrorResponse } from "@/types/error";

type Props = {
    onCreated: (user: User) => void; // プロパティ　関数
};

export default function SignupPage({ onCreated }: Props) {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState<FieldError[]>([]);

    /** 登録ボタン押下で動作 */
    const handleSubmit = async () => {
        try {
            const created = await createUser({name, email, password});
            /** 成功時はエラーをリセット */
            setErrors([]);
            /** 親へUserが作られたことを通知*/
            onCreated(created);
            alert("登録完了！");
        } catch(err: unknown) {
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

    /** ユーザ登録画面 */
    return (
        <>
            <div>
                <label htmlFor="name">ユーザー名：</label>
                <input 
                    value={name}
                    id="name"
                    onChange={e => setName(e.target.value)}
                    style={{
                            border: getErrorMessage("name") ? "2px solid red" : "1px solid #ccc",
                        }}
                />
                {getErrorMessage("name") && (
                        <p style={{ color: "red", fontSize: "0.9rem" }}>
                            {getErrorMessage("name")}</p>
                )}
            </div>
            <div>
                <label htmlFor="email">メールアドレス：</label>
                <input 
                    value={email}
                    id="email"
                    onChange={e => setEmail(e.target.value)}
                    style={{
                            border: getErrorMessage("email") ? "2px solid red" : "1px solid #ccc",
                        }}
                />
                {getErrorMessage("email") && (
                        <p style={{ color: "red", fontSize: "0.9rem" }}>
                            {getErrorMessage("email")}</p>
                )}
            </div>
            <div>
                <label htmlFor="password">パスワード：</label>
                <input 
                    value={password} 
                    id="password" 
                    onChange={e => setPassword(e.target.value)} 
                    style={{
                            border: getErrorMessage("password") ? "2px solid red" : "1px solid #ccc",
                        }}
                />
                {getErrorMessage("password") && (
                        <p style={{ color: "red", fontSize: "0.9rem" }}>
                            {getErrorMessage("password")}</p>
                )}
            </div>
            
            <button onClick={handleSubmit}>登録</button>
        </>
        
    );
}