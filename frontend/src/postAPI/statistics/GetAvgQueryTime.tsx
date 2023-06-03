import axiosBackend from ".."
import { AllAvgQueryTimeResp } from "..";

const GetAvgQueryTime = async function(): Promise<AllAvgQueryTimeResp> {
    const response = await axiosBackend
        .get("/statistics/queries/avgtime");

    return {
        status: response.status,
        content: response.data
    };
}

export default GetAvgQueryTime
