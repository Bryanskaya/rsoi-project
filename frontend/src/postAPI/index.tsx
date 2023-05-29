import { Category } from "types/Categories";
import { Hotel } from "types/Hotel";
import { Account } from "types/Account";
import axios from "axios";
import { HotelResponse } from "types/HotelResponse";

export const backUrl = "http://localhost:8080/api/v1";

const axiosBackend = () => {
    let instance = axios.create({
        baseURL: backUrl
    });

    instance.interceptors.request.use(function (config) {
        const token = localStorage.getItem("token");
        if (config.headers && token) {
            config.headers.Authorization = 'Bearer ' + token;
        }

        return config;
    })

    return instance;
}

export default axiosBackend();


export type AllHotelResp = {
    status: number,
    content: HotelResponse
}

export type AllCategoriesResp = {
    status: number,
    content: Category[]
}

export type AllUsersResp = {
    status: number,
    content: Account[]
}
