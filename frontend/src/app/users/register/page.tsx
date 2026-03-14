"use client";

import UserForm from "@/app/components/UserForm";
import { User } from "@/types/user";

export default function UserRegisterPage() {
    const hundleCreated = (user: User) => {
        console.log("親が受け取ったUser：", user);
    }

    return (
        <>
            <h1>ユーザー登録</h1>
            <UserForm onCreated={hundleCreated} />
        </>
    );
}