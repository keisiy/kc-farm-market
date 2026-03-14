import { User } from "@/types/user";

export type UserCreateRequest = {
    name: string,
    email: string,
    password: string
};

/** POST用API */
export async function createUser(
    data: UserCreateRequest
):Promise<User> {
    const response = await fetch("http://localhost:8080/api/users", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    });

    if(!response.ok) {
        /** response.json()で、javaScriptオブジェクトに変換している */
        const errorJson = await response.json();
        throw errorJson;
    } 

    return response.json();
};