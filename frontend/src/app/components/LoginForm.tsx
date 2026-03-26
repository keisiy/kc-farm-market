"use client";

/** 関数・型 */
import { useState } from "react";

export default function LoginForm() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async () => {
            const res = await fetch("http://localhost:8080/api/auth/login",{
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({ email, password}),
                }
            );

            if (res.ok) {
                alert("ログイン成功");
            } else {
                alert("ログイン失敗");
            }
    };

    return (
        <div>
            <h2>ログイン</h2>

            <div>
                <label>Email：</label>
                <input 
                    value={email}
                    placeholder="sample@com"
                    onChange={(e) => setEmail(e.target.value)}
                />
            </div>
            
            <div>
                <label>Password：</label>
                <input 
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="abcde"
                />
            </div> 
            
            <button onClick={handleLogin}>ログイン</button>
        </div>

    );
}