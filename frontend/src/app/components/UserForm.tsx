"use client";

import { useState } from "react";
import { createUser } from "@/lib/api/user";

export default function SignupPage() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    /** 登録ボタン押下で動作 */
    const handleSubmit = async () => {
        await createUser({name, email, password});
        alert("登録完了！");
    };

    /** ユーザ登録画面 */
    return (
        <>
            <div>
                <label htmlFor="name">ユーザー名：</label>
                <input value={name} id="name" onChange={e => setName(e.target.value)}></input>
            </div>
            <div>
                <label htmlFor="email">メールアドレス：</label>
                <input value={email} id="email" onChange={e => setEmail(e.target.value)}></input>
            </div>
            <div>
                <label htmlFor="password">パスワード：</label>
                <input value={password} id="password" onChange={e => setPassword(e.target.value)}></input>
            </div>
            
            <button onClick={handleSubmit}>登録</button>
        </>
        
    );
}