import type { AxiosRequestConfig, AxiosResponse } from 'axios';
import type { IResponse } from './types';
import { httpFactory } from './fetcher';

export const http = httpFactory();

const del = async <T = any>(url: string, config?: AxiosRequestConfig) => {
  return await http.delete<AxiosResponse<IResponse<T>>>(
    url,
    config,
  );
};

const post = async <T = any>(url: string, data?: any, config?: AxiosRequestConfig) => {
  return await http.post<AxiosResponse<IResponse<T>>>(
    url,
    data,
    config,
  );
};

const get = async <T = any>(url: string, config?: AxiosRequestConfig) => {
  return await http.get<AxiosResponse<IResponse<T>>>(
    url,
    config,
  );
};

const patch = async <T = any>(url: string, data: any, config?: AxiosRequestConfig) => {
  return await http.patch<AxiosResponse<IResponse<T>>>(
    url,
    data,
    config,
  );
};

const put = async <T = any>(url: string, data?: any, config?: AxiosRequestConfig) => {
  return await http.put<AxiosResponse<IResponse<T>>>(
    url,
    data,
    config,
  );
};

export const request = {
  delete: del,
  post,
  get,
  patch,
  put,
};

