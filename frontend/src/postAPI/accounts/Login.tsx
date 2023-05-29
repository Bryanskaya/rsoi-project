import axiosBackend from ".."
import { Account } from "types/Account";

interface resp {
  status: number
}

export const Login = async function (data: Account): Promise<resp> {
  const response = await axiosBackend
    .post(`/authorize`, data, { withCredentials: true })
    .then((data) => data)
    .catch((error) => {
      return { status: error.response?.status, data: error.response?.data };
    });

  if (response.status === 200) {
    localStorage.setItem("token", response.data.access_token);
    localStorage.setItem("username", data.username);
  }

  return {
    status: response?.status,
  };
};
