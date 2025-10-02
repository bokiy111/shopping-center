import { request } from '../core/http';
import { transformResponse } from '../tools/transform-response';
import { User } from '../../data';

export async function requestMyInfo() {
  const res = await request.get('/api/seller/getUserInfo');
  return transformResponse<User>(res);
}

export interface RequestUpdateMyInfoData {
  nickname: string;
  description: string;
  avatarUrl: string;
  qq: string;
  wechat: string;
  phone: string;
  email: string;
}

export async function requestUpdateMyInfo(data: RequestUpdateMyInfoData) {
  const res = await request.post('/api/updateUserInfo', data);
  return transformResponse<null>(res);
}
