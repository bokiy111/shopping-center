import { transformResponse } from '../tools/transform-response';
import { request } from '../core/http';
import { User } from '../../data';

export interface LoginRequest {
  account: string;
  password: string;
}

export async function requestLogin(data: LoginRequest) {
  const res = await request.post('/api/login', data);
  return transformResponse<User>(res);
}

export async function requestRegister(data: LoginRequest) {
  const res = await request.post('/api/register', data);
  return transformResponse<null>(res);
}
