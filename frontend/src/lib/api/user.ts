import { apiFetch } from "./client";

export type UserCreateRequest = {
    name: string,
    email: string,
    password: string
};

/** POST用API */
export const createUser = (data: UserCreateRequest) => {
    return apiFetch("/users", {method: "POST", body: JSON.stringify(data),});
};