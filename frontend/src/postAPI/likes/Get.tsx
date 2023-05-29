import axiosBackend from "..";
import { backUrl } from "..";

interface resp {
    status: number
    content: string
}

const GetImageUrl = async function(hotelUid: number): Promise<resp> {
    const response = await axiosBackend.get(backUrl + `/hotels/${hotelUid}/image`);
    return {
        status: response.status,
        content: response.data as string
    };
}
export default GetImageUrl;
