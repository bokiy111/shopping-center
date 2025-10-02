import type { AxiosResponse } from 'axios';
import type { IResponse } from '../core/types';

export const transformResponse = <T = any> (axiosResponse: AxiosResponse<IResponse<T>>): T => {
  if (axiosResponse.status >= 200 && axiosResponse.status < 300) {
    const res = axiosResponse.data;
    const err = axiosResponse as unknown as Error;

    if (res?.success) {
      return res?.data || {} as any;
    }

    throw new Error(res.message || err.message || 'Unknown Error');
  } else {
    throw new Error(`${axiosResponse.status}: ${axiosResponse.statusText}`);
  }
};
